package com.softwaresmithy;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import com.softwaresmithy.library.AndroidLibStatus;
import com.softwaresmithy.library.AndroidLibStatus.STATUS;
import com.softwaresmithy.library.LibStatusListener;
import com.softwaresmithy.library.Library;
import com.softwaresmithy.library.LibraryFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class NotificationService extends IntentService implements LibStatusListener {
  private final IBinder binder = new LocalBinder();
  private Library library;
  private WishlistDbAdapter mDbHelper;
  private NotificationManager mNotificationManager;
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
  private static ScheduledFuture updateProcess;
  private static ScheduledFuture cancelUpdates;

  private static final int DAY_IN_SECONDS = 60 * 60 * 24;

  public NotificationService() {
    super("NotificationService");
  }

  @Override
  public void onCreate() {
    super.onCreate();
    try {
      setLibrary(new LibraryFactory(getApplicationContext()).getLibrary());
      mDbHelper = new WishlistDbAdapter(getApplicationContext());
      mDbHelper.open();
    } catch (Exception e) {
      Log.e(NotificationService.class.getName(), "Unable to create a library :(", e);
    }
  }

  @Override
  public IBinder onBind(Intent arg0) {
    System.out.println("OMG Service!");
    if (mNotificationManager == null) {
      mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
      if (updateProcess == null || updateProcess.isCancelled()) {
        // Create a process to update all rows every day
        final Runnable updateStatus = new Runnable() {
          @Override
          public void run() {
            Cursor allBooks = mDbHelper.getAll();
            Log.i(NotificationService.class.getName(), "Starting to update books");
            while (allBooks.moveToNext()) {
              try {
                String isbn = allBooks.getString(allBooks.getColumnIndexOrThrow(WishlistDbAdapter.COL_ISBN));
                Log.d(NotificationService.class.getName(), "Updating " + isbn);
                getStatus(isbn);
              } catch (RuntimeException e) {
                e.printStackTrace();
              }
            }
          }
        };
        updateProcess = scheduler.scheduleAtFixedRate(updateStatus,
            DAY_IN_SECONDS,
            DAY_IN_SECONDS,
            TimeUnit.SECONDS);

        // Now create a process to stop those updates after the number of days set in the constants
        cancelUpdates = makeCancelTask();
      } else {
        // Reschedule for another period since you're obviously enjoying yourself
        cancelUpdates.cancel(false);
        cancelUpdates = makeCancelTask();
      }
    }
    return binder;
  }

  private ScheduledFuture<?> makeCancelTask() {
    return scheduler.schedule(new Runnable() {
      @Override
      public void run() {
        updateProcess.cancel(false);
      }
    }, DAY_IN_SECONDS * getResources().getInteger(R.integer.status_update_days), TimeUnit.SECONDS);
  }

  //
  public class LocalBinder extends Binder {
    NotificationService getService() {
      return NotificationService.this;
    }
  }

  private void getStatus(String isbn) {
    if (library instanceof AndroidLibStatus) {
      ((AndroidLibStatus) library).checkStatus(isbn);
    }
  }

  public void setLibrary(Library library) {
    this.library = library;
    if (library instanceof AndroidLibStatus) {
      ((AndroidLibStatus) library).addLibStatusListener(this);
    }
  }

  @Override
  public void onItemStatusChange(String isbn, STATUS result) {
    BookJB changedBook = mDbHelper.readItemByIsbn(isbn);
    String currentStatusStr = changedBook.getState();

    STATUS oldStatus = null;
    if (currentStatusStr != null) {
      oldStatus = STATUS.valueOf(currentStatusStr);
    }

    if (result != null) {
      changedBook.setState(result.name());
      mDbHelper.updateItem(changedBook);
    }

    if (result == STATUS.AVAILABLE && oldStatus != result) {
      Notification notification = new Notification(R.drawable.personreading,
          getString(R.string.app_name), System.currentTimeMillis());

      //TODO: Take the user to a view filtered by "AVAILABLE" titles
      Context context = getApplicationContext();
      Intent notificationIntent = new Intent("com.softwaresmithy.wishlist.FILTER", Uri.parse("filter://AVAILABLE"), context, Wishlist.class);
      PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);

      notification.setLatestEventInfo(context, getString(R.string.note_title),
          getString(R.string.note_body, changedBook.getTitle(), changedBook.getAuthor()), contentIntent);
      notification.flags |= Notification.FLAG_AUTO_CANCEL;
      notification.defaults |= Notification.DEFAULT_SOUND;

      mNotificationManager.notify(isbn, 0, notification);
    }
    sendBroadcast(new UpdateIntent(getString(R.string.broadcast_update), isbn, oldStatus, result));
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    getStatus(intent.getStringExtra("isbn"));
  }
}
