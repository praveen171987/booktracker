package com.softwaresmithy;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class Wishlist extends ListActivity {
	
	private int mNoteNumber = 1;
	private WishlistDbAdapter mDbHelper;
	
	//Same index used to map from database table column to layout ID in item view
//	private String[] mapFrom = WishlistDbAdapter.allColumns;
	private String[] mapFrom = new String[]{WishlistDbAdapter.allColumns[3]};	
	private int[] mapTo = new int[]{R.id.text1};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mDbHelper = new WishlistDbAdapter(this);
        mDbHelper.open();
        fillData();
    }

    private void fillData(){
    	//Get all rows from DB
    	Cursor c = mDbHelper.getAll();
    	startManagingCursor(c);
    	
    	SimpleCursorAdapter items = new SimpleCursorAdapter(this, R.layout.list_item, c, mapFrom, mapTo);
    	setListAdapter(items);
    }
}