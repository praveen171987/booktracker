package com.softwaresmithy;

import java.util.ArrayList;
import java.util.List;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import com.softwaresmithy.library.AndroidLibStatus;
import com.softwaresmithy.library.AndroidLibStatus.STATUS;
import com.softwaresmithy.library.LibStatusListener;
import com.softwaresmithy.library.Library;

public class NotificationService extends Service implements LibStatusListener {
	private final IBinder binder = new LocalBinder();
	private Library library;
	private WishlistDbAdapter mDbHelper;
	private NotificationManager mNotificationManager;
	
	private List<LibStatusListener> listeners = new ArrayList<LibStatusListener>();
	
	public NotificationService(){
		super();
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		System.out.println("OMG Service!");
		if(mNotificationManager == null){
			mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		}
		return binder;
	}
	
	public class LocalBinder extends Binder {
		NotificationService getService() {
			return NotificationService.this;
		}
	}

	public void getStatus(String isbn){
		if(library instanceof AndroidLibStatus){
			((AndroidLibStatus)library).checkStatus(isbn);
		}
	}
	
	public void setLibrary(Library library) {
		this.library = library;
		if(library instanceof AndroidLibStatus ) {
			((AndroidLibStatus)library).addLibStatusListener(this);
		}
	}
	
	public void setDatabase(WishlistDbAdapter adapter) {
		mDbHelper = adapter;
	}

	@Override
	public void onItemStatusChange(String isbn, STATUS result) {
		BookJB changedBook = mDbHelper.readItemByIsbn(isbn);
		String currentStatusStr = changedBook.getState();
		
		STATUS oldStatus = null;
		if(currentStatusStr != null){
			oldStatus = STATUS.valueOf(currentStatusStr);
		}
		
		if(result != null) {
			changedBook.setState(result.name());
			mDbHelper.updateItem(changedBook);
		}
		
		if(result == STATUS.AVAILABLE && oldStatus != result){
			Notification notification = new Notification(R.drawable.personreading, 
					getString(R.string.app_name), System.currentTimeMillis());
			Context context = getApplicationContext();
			Intent notificationIntent = new Intent(context, Wishlist.class);
			PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);
			
			notification.setLatestEventInfo(context, getString(R.string.note_title), 
						getString(R.string.note_body, changedBook.getTitle(), changedBook.getAuthor()), contentIntent);
			notification.flags |= Notification.FLAG_AUTO_CANCEL;
			notification.defaults |= Notification.DEFAULT_SOUND;
			
			mNotificationManager.notify(isbn, 0, notification);
		}else if(result != null) {
			//Let the user know we did *some*thing
			Toast.makeText(this, "new status is: "+result.name(), Toast.LENGTH_LONG).show();
		}
		
		for(LibStatusListener listener : listeners ) {
			//percolate the changes.
			listener.onItemStatusChange(isbn, result);
		}
	}
	
	public void registerLibStatusListener(LibStatusListener listener ) {
		listeners.add(listener);
	}
	
	public void unregisterLibStatusListener(LibStatusListener listener) {
		listeners.remove(listener);
	}
}
