package com.softwaresmithy;

import android.content.Context;
import android.os.AsyncTask;

import com.softwaresmithy.library.LibStatus;
import com.softwaresmithy.library.LibStatus.STATUS;
import com.softwaresmithy.library.Library;
import com.softwaresmithy.metadata.MetadataProvider;

/**
 * Capsule class to perform network operations to gather metadata in a background thread,
 * and off the android UI thread. Current operations are
 * 1) gathering text metadata
 * 2) save to the database
 * 3) retrieve and save thumbnail image
 * 4) retrieve status from library (and update database row)
 * 
 * @author SZY4ZQ
 *
 */
public class DownloadDataTask extends AsyncTask<String, Void, Boolean> {
	/**
	 * Passed in application context.
	 */
	private Context c;
	/**
	 * Passed in database accessor.
	 */
	private WishlistDbAdapter mDbHelper;
	/**
	 * library accessor.
	 */
	private Library library;
	/**
	 * metadata accessor.
	 */
	private MetadataProvider data;
	
	/**
	 * Default constructor, just sets parameters to local class variables.
	 * @param c application context
	 * @param db database accessor
	 * @param l library status provider
	 * @param dp metadata provider
	 */
	public DownloadDataTask(Context c, WishlistDbAdapter db, Library l, MetadataProvider dp){
		this.c = c;
		this.mDbHelper = db;
		this.library = l;
		this.data = dp;
	}
	
	@Override
	protected Boolean doInBackground(String... params) {
		String isbn = params[0];
		//TODO: Surround with try/catch, catch net exception, throw to manual edit page with error
		//Retrieve data from data provider and persist to database
		BookJB jb = addItemToDB(isbn);
		if(jb != null){
			//Retrieve and persist thumbnail image
			data.saveThumbnail(c, jb.getVolumeId(), jb.getThumbUrl());
			
			STATUS retVal = null;
			if(library instanceof LibStatus){
				retVal = ((LibStatus)library).checkAvailability(isbn);
				if(retVal != null){
					jb.setState(retVal.name());
					mDbHelper.updateItem(jb);
				}
			}
		}
		return jb != null;
	}

	/**
	 * Looks up item metadata based on passed ISBN number then stores that info in the DB.
	 * @param isbn ISBN Number
	 * @return BookJB populated with metadata
	 */
    private BookJB addItemToDB(String isbn) {
    	BookJB jb = data.getInfo(isbn);
    	if(jb != null){
    		long id = mDbHelper.createItem(jb);
    		jb.set_id(id);
    		return jb;
    	}
    	return null;
    }
}
