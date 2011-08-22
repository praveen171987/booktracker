package com.softwaresmithy;

import com.softwaresmithy.library.AndroidLibStatus;
import com.softwaresmithy.library.AndroidLibStatus.STATUS;
import com.softwaresmithy.library.LibStatusListener;
import com.softwaresmithy.library.Library;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class NotificationService extends Service implements LibStatusListener {
	private final IBinder binder = new LocalBinder();
	private Library library;
	private WishlistDbAdapter mDbHelper;
	private NotificationManager mNotificationManager;
	
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
		String currentStatusStr = mDbHelper.readItemByIsbn(isbn).getState();
		
		STATUS oldStatus = null;
		if(currentStatusStr != null){
			STATUS.valueOf(currentStatusStr);
		}
		if(result != null && oldStatus != result){
			Toast.makeText(this, "new status is: "+result.name(), Toast.LENGTH_LONG).show();
			Notification notification = new Notification(R.drawable.unknown, "Hello", System.currentTimeMillis());
			Context context = getApplicationContext();
			String contentTitle = "This is a Title";
			String contentText = "This is some text";
			Intent notificationIntent = new Intent(context, Wishlist.class);
			PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);
			
			notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
			notification.flags |= Notification.FLAG_AUTO_CANCEL;
			notification.defaults |= Notification.DEFAULT_SOUND;
			
			mNotificationManager.notify(isbn, 0, notification);
			
		}
		
	}
}
