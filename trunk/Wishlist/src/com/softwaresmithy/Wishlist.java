package com.softwaresmithy;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Wishlist extends ListActivity {
	
	private int mNoteNumber = 1;
	private WishlistDbAdapter mDbHelper;
	private EditText isbnInput;
	
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
        //initialize text input listener
        isbnInput = (EditText)findViewById(R.id.input_isbn); 
        isbnInput.setOnKeyListener(new OnKeyListener(){
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
					createItem(isbnInput.getText().toString());
					return true;
				}
				return false;
			}
        });
        fillData();
    }

    private void fillData(){
    	//Get all rows from DB
    	Cursor c = mDbHelper.getAll();
    	startManagingCursor(c);
    	
    	SimpleCursorAdapter items = new SimpleCursorAdapter(this, R.layout.list_item, c, mapFrom, mapTo);
    	setListAdapter(items);
    }
    
    private void createItem(String isbn){
    	Toast.makeText(this, isbn, Toast.LENGTH_SHORT).show();
		isbnInput.setVisibility(View.GONE);
		isbnInput.setText("");
    }
    /*Menu related functions*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()){
    	case R.id.add_text:
    		isbnInput.setVisibility(View.VISIBLE);
    		isbnInput.requestFocus();
    		return true;
    	case R.id.add_barcode:
    		//Call external scanner via Intent
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
}