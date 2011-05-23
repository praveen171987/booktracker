package com.softwaresmithy;

import java.io.File;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;

import com.softwaresmithy.library.LibStatus.STATUS;

public class ImageCursorAdapter extends SimpleCursorAdapter implements Filterable{
	private Cursor c;
	private String imagePath;
	private Context context;
	private WishlistDbAdapter mDbHelper;

	public ImageCursorAdapter(Context context, WishlistDbAdapter mDbHelper, int layout, Cursor c, String[] from, int[] to, String imagePath) {
		super(context, layout, c, from, to);
		this.c = c;
		this.imagePath = imagePath;
		this.context = context;
		this.mDbHelper = mDbHelper;
		
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		View retView = super.getView(position, v, parent);
		
		c.moveToPosition(position);
		String volumeId = c.getString(c.getColumnIndexOrThrow(WishlistDbAdapter.COL_VOLUME_ID));
		
		View state = retView.findViewById(R.id.item_state);
		
		String statusStr = c.getString(c.getColumnIndexOrThrow(WishlistDbAdapter.COL_STATE));
		if(statusStr != null){
			STATUS status = STATUS.valueOf(statusStr);
			switch(status){
			case NO_MATCH:
				state.setBackgroundColor(0xFF000000);
				break;
			case AVAILABLE:
				state.setBackgroundColor(0xFFFFFFFF);
				break;
			case SHORT_WAIT:
				state.setBackgroundColor(0xFF66CCFF);
				break;
			case WAIT:
				state.setBackgroundColor(0xFF0066FF);
				break;
			case LONG_WAIT:
				state.setBackgroundColor(0xFF0000CC);
				break;
			}
		}
		ImageView iv = (ImageView) retView.findViewById(R.id.cover);
		File file = new File(imagePath,volumeId+".jpg");
		Bitmap cover;
		if(file.exists()){
			cover = BitmapFactory.decodeFile(file.getAbsolutePath());
		}else{
			cover = BitmapFactory.decodeResource(context.getResources(), R.drawable.unknown);
		}
		iv.setImageBitmap(cover);
		return retView;
	}
	
	public Cursor filterByStatus(CharSequence constraint){
		System.out.println("all rows count: "+mDbHelper.getAll().getCount());
		if(constraint == null){
			return mDbHelper.getAll();
		}else{
			System.out.println(constraint+" count: "+mDbHelper.filterByStatus(constraint).getCount());
			return mDbHelper.filterByStatus(constraint);
		}		
	}
}
