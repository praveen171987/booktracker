package com.softwaresmithy;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.format.DateFormat;
import android.widget.RemoteViews;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: matt
 * Date: 11/20/11
 * Time: 3:41 PM
 */
public class ImportExportTask extends AsyncTask<Context, Integer, Integer> {
  private NotificationManager notificationManager;
  private Notification notification;
  private WishlistDbAdapter mDbHelper;
  //Filename format 'WishlistLibrary_yyyy-MM-dd.csv';
  private static final String fileFormat = "'WishlistLibrary_'yyyy-MM-dd'.csv'";

  @Override
  protected Integer doInBackground(Context... contexts) {
    Context appContext = contexts[0];
    notificationManager = (NotificationManager) appContext.getSystemService(Context.NOTIFICATION_SERVICE);
    mDbHelper = new WishlistDbAdapter(appContext);
    mDbHelper.open();

    File wishlistDir = new File(Environment.getExternalStorageDirectory(), "Wishlist");
    boolean mkdir;
    if (!wishlistDir.exists()) {
      mkdir = wishlistDir.mkdir();
      if (!mkdir) {
        //TODO: this is a disaster, I can't write anything to storage
        return null;
      }
    }

    try {
      File exportFile = new File(wishlistDir, DateFormat.format(fileFormat, System.currentTimeMillis()).toString());
      BufferedWriter writer = new BufferedWriter(new FileWriter(exportFile));

      notification = new Notification(android.R.drawable.stat_sys_upload, "Exporting Wishlist Library", System.currentTimeMillis());
      notification.flags |= Notification.FLAG_ONGOING_EVENT;
      notification.flags |= Notification.FLAG_ONLY_ALERT_ONCE;
      notification.setLatestEventInfo(appContext, "Title", "Text", null);
      notification.contentView = new RemoteViews(ImportExportTask.class.getPackage().getName(), R.layout.import_export_progress_notification);
      notification.contentView.setTextViewText(R.id.status_text, "This is a test");
      notification.contentView.setProgressBar(R.id.status_progress, 100, 0, false);
      notification.contentIntent = PendingIntent.getActivity(appContext, 0, new Intent(appContext, Wishlist.class), 0);
      notificationManager.notify(0, notification);

      //First, write the file header and schema version (used mostly for identification during import)
      writer.append("Wishlist Schema v" + mDbHelper.getVersion());
      //Second, write the column headers
      //Third, use the cursor to write out all the details
      Cursor allBooks = mDbHelper.getAll();
      int numRecords = allBooks.getCount();
      for (int col = 0; col < allBooks.getColumnCount(); col++) {
        if (col != 0) {
          writer.append('\t');
        }
        writer.append(allBooks.getColumnName(col));
      }
      writer.append('\n');
      int currentRecord = 0;
      while (allBooks.moveToNext()) {
        for (int col = 0; col < allBooks.getColumnCount(); col++) {
          if (col != 0) {
            writer.append('\t');
          }
          writer.append(allBooks.getString(col));
        }
        currentRecord++;
        publishProgress(currentRecord / numRecords);
        writer.append('\n');
      }
      writer.flush();
      writer.close();

      // Scan the file to get it to show up in MTP (the USB Mass Storage replacement)
      MediaScannerConnection.scanFile(appContext, new String[]{exportFile.getPath()}, null, null);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  protected void onProgressUpdate(Integer... values) {
//    super.onProgressUpdate(values);
    //TODO: This needs to be run on the UI thread, best suggestion is a helper class
//    notification.contentView.setProgressBar(R.id.status_progress, 100, values[0], false);
//    notification.notify();
  }

  @Override
  protected void onPostExecute(Integer integer) {
    super.onPostExecute(integer);
    notification.flags &= ~Notification.FLAG_ONGOING_EVENT;
    notification.contentView.setProgressBar(R.id.status_progress, 100, 100, false);
    notification.icon = android.R.drawable.stat_sys_upload_done;
    notificationManager.notify(0, notification);
  }
}
