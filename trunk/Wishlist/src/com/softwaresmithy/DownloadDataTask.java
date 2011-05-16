package com.softwaresmithy;

import android.content.Context;
import android.os.AsyncTask;

import com.softwaresmithy.library.LibStatus;
import com.softwaresmithy.library.LibStatus.STATUS;
import com.softwaresmithy.library.Library;
import com.softwaresmithy.library.impl.WebPac;
import com.softwaresmithy.metadata.MetadataProvider;

public class DownloadDataTask extends AsyncTask<String, Void, Boolean> {
	private Context c;
	private WishlistDbAdapter mDbHelper;
	private Library library;
	private MetadataProvider data;
	
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
			data.saveThumbnail(c, jb.getVolume_id(), jb.getThumbUrl());;
			
			STATUS retVal = null;
			LibStatus lib = new WebPac();
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
