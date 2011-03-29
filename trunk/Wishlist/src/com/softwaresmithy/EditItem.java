package com.softwaresmithy;

import java.io.File;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class EditItem extends Activity {
	private WishlistDbAdapter dbHelper;
	private Long rowId;
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
		if(rowId != null){
			Cursor item = dbHelper.readItem(rowId);
			startManagingCursor(item);
			getEditView(R.id.title_text).setText(item.getString(
					item.getColumnIndexOrThrow(WishlistDbAdapter.COL_TITLE)));
			getEditView(R.id.author_text).setText(item.getString(
					item.getColumnIndexOrThrow(WishlistDbAdapter.COL_AUTHOR)));
			String isbn = item.getString(
					item.getColumnIndexOrThrow(WishlistDbAdapter.COL_ISBN));
			getEditView(R.id.isbn_text).setText(isbn);
			Bitmap cover;
			File file = new File(getExternalCacheDir(),isbn+".jpg");
			if(file.exists()){
				cover = BitmapFactory.decodeFile(file.getAbsolutePath());
			}else{
				cover = BitmapFactory.decodeResource(this.getResources(), R.drawable.unknown);
			}
			getCoverView().setImageBitmap(cover);
		}
	}
	
	private ImageView getCoverView(){
		return (ImageView) findViewById(R.id.cover);
	}
	private EditText getEditView(int id){
		return (EditText) findViewById(id);
	}
}
