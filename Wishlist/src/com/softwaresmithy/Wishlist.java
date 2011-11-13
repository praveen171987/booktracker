package com.softwaresmithy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.*;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.*;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnKeyListener;
import android.widget.*;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.softwaresmithy.NotificationService.LocalBinder;
import com.softwaresmithy.library.AndroidLibStatus;
import com.softwaresmithy.library.AndroidLibStatus.STATUS;
import com.softwaresmithy.library.LibStatusListener;
import com.softwaresmithy.library.Library;
import com.softwaresmithy.library.LibraryFactory;
import com.softwaresmithy.metadata.DataProviderFactory;
import com.softwaresmithy.metadata.MetadataProvider;
import com.softwaresmithy.preferences.Preferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Wishlist extends ListActivity implements LibStatusListener {

  private WishlistDbAdapter mDbHelper;
  private EditText isbnInput;
  private ImageCursorAdapter listData;
  private Cursor listCursor;

  private Library library;
  private MetadataProvider data;

  private long selectedItem;

  //Intent request states
  private static final int ACTIVITY_CREATE = 0;
  private static final int ACTIVITY_EDIT = 1;
  private static final int SET_PREFS = 2;

  private HashSet<STATUS> currentStateFilter = new HashSet<STATUS>();
  private String currentStringFilter = "";

  //Same index used to map from database table column to layout ID in item view
  private String[] mapFrom = new String[]{WishlistDbAdapter.COL_TITLE, WishlistDbAdapter.COL_AUTHOR};
  private int[] mapTo = new int[]{R.id.title, R.id.author};
  private NotificationService localService;
  private ServiceConnection serviceConnection = new ServiceConnection() {

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
      System.out.println("service connected!");
      localService = ((LocalBinder) service).getService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
      System.out.println("service unconnected!");
      localService = null;
    }

  };
  private UpdateReceiver fReceiver = new UpdateReceiver();
  private List<String> expectingResponse = new ArrayList<String>();

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.main);
    registerForContextMenu(getListView());

    //TODO: How do I get one of those screens that runs on the initial load of the application but not any other time?
    try {
      SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
      String prefVal = prefs.getString(getString(R.string.pref_key_libChoice), null);
      if (prefVal == null) {
        //Show preferences on initial load
        Intent i = new Intent(this, Preferences.class);
        startActivityForResult(i, SET_PREFS);
      } else {
        library = new LibraryFactory(this).getLibrary();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    data = new DataProviderFactory().getDataProvider();

    getListView().setOnItemClickListener(new OnItemClickListener() {
      public void onItemClick(AdapterView<?> parent, View view,
                              int position, long id) {
        ListView list = ((ListView) parent);
        Cursor c = (Cursor) list.getItemAtPosition(position);
        BookJB jb = mDbHelper.readItemByIsbn(c.getString(c.getColumnIndexOrThrow(WishlistDbAdapter.COL_ISBN)));

        if (library instanceof AndroidLibStatus) {
          Intent intent = new Intent(Intent.ACTION_VIEW, ((AndroidLibStatus) library).getStatusPage(jb.getIsbn()));
          startActivity(intent);
        }
      }
    });

    mDbHelper = new WishlistDbAdapter(this);
    mDbHelper.open();
    //initialize text input listener
    isbnInput = (EditText) findViewById(R.id.input_isbn);
    isbnInput.setOnKeyListener(new OnKeyListener() {
      @Override
      public boolean onKey(View v, int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
          createItem(isbnInput.getText().toString());
          return true;
        }
        return false;
      }
    });
    listCursor = mDbHelper.getAll();
    startManagingCursor(listCursor);

    //TODO: getExternalCacheDir is null if you're in USB Connect mode, check for that!
    listData = new ImageCursorAdapter(this, mDbHelper, R.layout.list_item, listCursor, mapFrom, mapTo, getExternalCacheDir().getAbsolutePath());
    setListAdapter(listData);

  }

  @Override
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
    getMenuInflater().inflate(R.menu.item_menu, menu);
    menu.findItem(R.id.metadata_page).setTitle(getResources().getString(R.string.view_metadata, data.getProviderName()));
  }

  @Override
  public boolean onContextItemSelected(MenuItem item) {
    final AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    Cursor c = mDbHelper.readItem(info.id);
    switch (item.getItemId()) {
      case R.id.edit_item:
        Intent i = new Intent(this, EditItem.class);
        i.putExtra(WishlistDbAdapter.COL_ID, info.id);
        startActivityForResult(i, ACTIVITY_EDIT);
        break;
      case R.id.delete_item:
        selectedItem = info.id;
        showDialog(DIALOG_DELETE_ITEM);
        break;
      case R.id.update_item:
        String isbn = c.getString(
            c.getColumnIndexOrThrow(WishlistDbAdapter.COL_ISBN));
        expectingResponse.add(isbn);
        startService(getStatusIntent(isbn));
        break;
      case R.id.metadata_page:
        BookJB jb = mDbHelper.getItemFromCursor(c);
        Intent intent = new Intent(Intent.ACTION_VIEW, data.getBookInfoPage(jb));
        startActivity(intent);
        break;
      default:
        Log.w(this.getClass().getName(), "Unexpected context menu item selected");
    }
    return super.onContextItemSelected(item);
  }

  private void fillData() {
    //Get all rows from DB
    listCursor.requery();
  }

  private Intent getStatusIntent(String isbn) {
    Intent statusIntent = new Intent(this, NotificationService.class);
    statusIntent.putExtra("isbn", isbn);

    return statusIntent;
  }

  private void createItem(String isbn) {
    if (validIsbn(isbn)) {
      isbnInput.setVisibility(View.GONE);
      isbnInput.setText("");
      //I have no idea if an anonymous class like this is appropriate, but it's what I'm going with for the nonce
      new DownloadDataTask(this, mDbHelper, library, data) {
        @Override
        protected void onPostExecute(Boolean result) {
          if (result) {
            //Refresh UI
            fillData();
          } else {
            Log.e(this.getClass().getName(), "Fuck, bad return value");
          }
          super.onPostExecute(result);
        }
      }.execute(isbn);
    } else {
      //TODO: Change this to an abort/continue dialog, (small) chance it's legitimately wrong but accurate
      Toast.makeText(this, isbn + " is not a valid ISBN", Toast.LENGTH_SHORT).show();
    }
  }

  /*Menu related functions*/
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.add_text:
        isbnInput.setVisibility(View.VISIBLE);
        isbnInput.requestFocus();
        return true;
      case R.id.add_barcode:
        //TODO: customize text?
        AlertDialog alert = IntentIntegrator.initiateScan(this);
        if (alert != null) {
          alert.show();
        }
        return true;
      case R.id.add_scratch:
        Intent i = new Intent(this, EditItem.class);
        startActivityForResult(i, ACTIVITY_CREATE);
        return true;
      case R.id.filter:
        showDialog(DIALOG_FILTER);
        return true;
      case R.id.preferences:
        Intent j = new Intent(this, Preferences.class);
        startActivity(j);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode) {
      case IntentIntegrator.REQUEST_CODE:
        IntentResult res = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String scanContents;
        if (res != null) {
          scanContents = res.getContents();
        } else {
          return;
          //Nothing to see here, move along
        }
        createItem(scanContents);
        return;
      case SET_PREFS:
        try {
          //TODO: This is a whole lot more complicated, if they've actually changed their library, should we delete their current statuses?
          //What's the workflow for regenerating all that data?
          library = new LibraryFactory(this).getLibrary();
        } catch (Exception e) {
          Log.e(this.getClass().getName(), "error setting library from preferences", e);
        }
        return;
      default: //unknown request code
    }
  }

  private static final int DIALOG_FILTER = 1;
  private static final int DIALOG_DELETE_ITEM = 2;

  @Override
  protected Dialog onCreateDialog(int id) {

    switch (id) {
      case DIALOG_FILTER:
        LayoutInflater factory = LayoutInflater.from(this);
        final View filterView = factory.inflate(R.layout.filter, null);
        ListView filterList = (ListView) filterView.findViewById(R.id.filter_list);
        String[] items = getResources().getStringArray(R.array.status_text);
        boolean[] show = new boolean[items.length];
        Arrays.fill(show, true);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        filterList.setAdapter(adapter);
        return new AlertDialog.Builder(this)
            .setTitle(getString(R.string.status_filter))
            .setMultiChoiceItems(items, show, new DialogInterface.OnMultiChoiceClickListener() {

              @Override
              public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                STATUS status = STATUS.valueOf(getResources().getStringArray(R.array.status_enum)[which]);
                if (isChecked) {
                  currentStateFilter.remove(status);
                } else {
                  currentStateFilter.add(status);
                }
                listCursor = listData.filterOutByStatus(currentStateFilter);
                listData = new ImageCursorAdapter(getApplicationContext(), mDbHelper, R.layout.list_item, listCursor, mapFrom, mapTo, getExternalCacheDir().getAbsolutePath());
                setListAdapter(listData);

              }
            })
            .create();
      case DIALOG_DELETE_ITEM:
        return new AlertDialog.Builder(this)
            .setTitle(R.string.del_title)
            .setMessage(R.string.del_message)
            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

              @Override
              public void onClick(DialogInterface dialog, int which) {
                mDbHelper.deleteItem(selectedItem);
                fillData();
              }
            })
            .setNegativeButton(R.string.no, null)
            .create();
      default:
        Log.w(this.getClass().getName(), "Unexpected dialog selected");
    }
    return super.onCreateDialog(id);
  }

  public boolean validIsbn(String isbn) {
    char[] nums = isbn.toCharArray();
    if (nums.length == 10) {
      if (nums[9] == 'X' || nums[9] == 'x') {
        nums[9] = 10 + '0'; //So that the subtraction below works out correctly
      }
      int a = 0, b = 0;
      for (int i = 0; i < 10; i++) {
        a += nums[i] - '0';
        b += a;
      }
      return b % 11 == 0;
    } else if (nums.length == 13) {
      int s = 0;
      for (int i = 0; i < 12; i++) {
        s += (nums[i] - '0') * (1 + (2 * (i % 2)));
      }
      return (10 - (s % 10)) % 10 == nums[12] - '0';
    }
    return false;
  }

  @Override
  protected void onResume() {
    super.onResume();
    // Instantiate the updater service
    bindService(new Intent(this, NotificationService.class), serviceConnection, Context.BIND_AUTO_CREATE);
    registerReceiver(fReceiver, new IntentFilter(getString(R.string.broadcast_update)));
  }

  @Override
  protected void onPause() {
    super.onPause();
    unbindService(serviceConnection);
    unregisterReceiver(fReceiver);
  }

  @Override
  public void onItemStatusChange(String isbn, STATUS result) {
    //refresh the screen when a status has changed
    fillData();

  }

  public class UpdateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
      UpdateIntent update = new UpdateIntent(intent);
      //Only show a Toast if it was a user initiated request.
      if (expectingResponse.contains(update.getIsbn()) &&
          update.getNewStatus() != null) {
        expectingResponse.remove(update.getIsbn());
        BookJB jb = mDbHelper.readItemByIsbn(update.getIsbn());

        // 1) If the new status is the same as the old status, say that
        // 2) If the new status is different and anything but AVAILABLE, say that
        // 3) If the new status is different and AVAILABLE, then a notification is delivered by the Notification
        //      Service
        if (update.getOldStatus().equals(update.getNewStatus())) {
          Toast.makeText(Wishlist.this,
              getString(R.string.same_status, jb.getTitle(),
                  getLocalizedStatus(update.getNewStatus())),
              Toast.LENGTH_LONG).show();
        } else if (update.getNewStatus() != STATUS.AVAILABLE) {
          Toast.makeText(Wishlist.this,
              getString(R.string.new_status, jb.getTitle(),
                  getLocalizedStatus(update.getNewStatus())),
              Toast.LENGTH_LONG).show();
        }
      }
      fillData();
    }
  }

  private String getLocalizedStatus(STATUS status) {
    return getResources().getStringArray(R.array.status_text)[status.ordinal()];
  }
}
