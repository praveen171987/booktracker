package com.softwaresmithy.library;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import android.net.Uri;
import android.os.AsyncTask;

public abstract class AndroidLibStatus extends Library {
	public enum STATUS {
		NO_MATCH, //Not available at the library
		AVAILABLE, //Copies available
		SHORT_WAIT, //H = #Holds, C = #Copies, H < C
		WAIT, // C <= H <= 2C
		LONG_WAIT // H > 2C
	};
	
	protected abstract STATUS checkAvailability(String isbn);
	public abstract Uri getStatusPage(String isbn);
	
	private List<LibStatusListener> fListeners = new ArrayList<LibStatusListener>();
	
	public void addLibStatusListener(LibStatusListener aNewListener){
		fListeners.add(aNewListener);
	}
	
	public void removeLibStatusListener(LibStatusListener aOldListener) {
		fListeners.remove(aOldListener);
	}
	
	public void checkStatus(String isbn) {
		new CheckStatus().execute(isbn);
	}
	
	
	private class CheckStatus extends AsyncTask<String, Void, STATUS>{
		private String isbn;
		
		@Override
		protected STATUS doInBackground(String... params) {
			isbn = params[0];
			return checkAvailability(isbn);
		}

		@Override
		protected void onPostExecute(STATUS result) {
			super.onPostExecute(result);
			
			for(LibStatusListener listener : fListeners ) {
				listener.onItemStatusChange(isbn, result);
			}
		}
	}
}
