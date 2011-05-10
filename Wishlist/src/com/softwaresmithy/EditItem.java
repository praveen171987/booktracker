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

public class EditItem extends Activity {
	private WishlistDbAdapter dbHelper;
	private Long rowId;
	private Cursor item;
	public static final String ROWID = "row_id";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbHelper = new WishlistDbAdapter(this);
		dbHelper.open();

		setContentView(R.layout.edit_item);
		
		Bundle extras = getIntent().getExtras();
		rowId = extras != null ? extras.getLong(ROWID) : null;
		
		populateFields();
	}
	
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
	
	private ImageView getCoverView(){
		return (ImageView) findViewById(R.id.cover);
	}
	private EditText getEditView(int id){
		return (EditText) findViewById(id);
	}
	//onClick listeners:
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
	public void onDiscardButtonClick(View button){
		finish();
	}
}
