package com.softwaresmithy;

import java.io.File;
import java.util.Date;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Simple activity for manually editing metadata through a UI screen.
 * 
 * TODO: Update based on database changes (volume ID)
 * @author SZY4ZQ
 *
 */
public class EditItem extends Activity {
	/**
	 * Database accessor.
	 */
	private WishlistDbAdapter dbHelper;
	/**
	 * Primary key in DB table.
	 */
	private Long rowId;
	/**
	 * One row cursor for retrieving data from the DB.
	 */
	private Cursor item;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbHelper = new WishlistDbAdapter(this);
		dbHelper.open();

		setContentView(R.layout.edit_item);
		
		Bundle extras = getIntent().getExtras();
		rowId = extras != null ? extras.getLong(WishlistDbAdapter.COL_ID) : null;
		
		populateFields();
	}
	
	/**
	 * Set values into layout fields, retrieve thumbnail from filesystem (if exists)
	 * and set into view. 
	 */
	private void populateFields(){
		Bitmap cover;
		if(rowId != null){
			item = dbHelper.readItem(rowId);
			startManagingCursor(item);
			getEditView(R.id.title_text).setText(item.getString(
					item.getColumnIndexOrThrow(WishlistDbAdapter.COL_TITLE)));
			getEditView(R.id.author_text).setText(item.getString(
					item.getColumnIndexOrThrow(WishlistDbAdapter.COL_AUTHOR)));
			String isbn = item.getString(
					item.getColumnIndexOrThrow(WishlistDbAdapter.COL_ISBN));
			getEditView(R.id.isbn_text).setText(isbn);
			File file = new File(getExternalCacheDir(),isbn+".jpg");
			if(file.exists()){
				cover = BitmapFactory.decodeFile(file.getAbsolutePath());
			}else{
				cover = BitmapFactory.decodeResource(this.getResources(), R.drawable.unknown);
			}
		}else {
			cover = BitmapFactory.decodeResource(this.getResources(), R.drawable.unknown);
		}
		getCoverView().setImageBitmap(cover);

	}
	
	/**
	 * Convenience method to look up cover view by id.
	 * @return container to hold cover art
	 */
	private ImageView getCoverView(){
		return (ImageView) findViewById(R.id.cover);
	}
	/**
	 * Convenience method to look up EditText views by id.
	 * @param id Android R id of the requested EditText
	 * @return the EditText appropriately cast
	 */
	private EditText getEditView(int id){
		return (EditText) findViewById(id);
	}
	/**
	 * Callback method specified in edit_item.xml layout for the 'Save' button.
	 * If a unique ID is present (from populateFields) then an update operation is performed
	 * else, a new entry is created.
	 * @param button View that was clicked (unused)
	 * TODO: refactor to a single onclick method using the button.getId() method in a switch?
	 */
	public void onSaveButtonClick(View button){
		//TODO: Refactor this, this is awful cursor to java helper method?
		if(rowId != null){
			dbHelper.updateItem(new BookJB(
					rowId, 
					getEditView(R.id.isbn_text).getText().toString(), 
					item.getString(item.getColumnIndexOrThrow(WishlistDbAdapter.COL_VOLUME_ID)), 
					getEditView(R.id.title_text).getText().toString(), 
					getEditView(R.id.author_text).getText().toString(), 
					new Date(item.getLong(item.getColumnIndexOrThrow(WishlistDbAdapter.COL_PUB_DATE))),
					new Date(item.getLong(item.getColumnIndexOrThrow(WishlistDbAdapter.COL_ADD_DATE))),
					null,//getEditView(R.id.duedate_text).getText().toString(), 
					null, 
					null));
		}else { //creating a new one
			dbHelper.createItem(new BookJB(
					null, 
					getEditView(R.id.isbn_text).getText().toString(), 
					item.getString(item.getColumnIndexOrThrow(WishlistDbAdapter.COL_VOLUME_ID)), 
					getEditView(R.id.title_text).getText().toString(), 
					getEditView(R.id.author_text).getText().toString(), 
					new Date(item.getLong(item.getColumnIndexOrThrow(WishlistDbAdapter.COL_PUB_DATE))),
					new Date(item.getLong(item.getColumnIndexOrThrow(WishlistDbAdapter.COL_ADD_DATE))),
					null,//getEditView(R.id.duedate_text).getText().toString(), 
					null, 
					null));
		}
		finish();
	}
	/**
	 * Close the action and return to the calling Activity without persisting changes.
	 * @param button the button that was clicked.
	 */
	public void onDiscardButtonClick(View button){
		finish();
	}
}
