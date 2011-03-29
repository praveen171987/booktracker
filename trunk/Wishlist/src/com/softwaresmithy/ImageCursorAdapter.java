package com.softwaresmithy;

import java.io.File;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;

public class ImageCursorAdapter extends SimpleCursorAdapter {
	private Cursor c;
	private String imagePath;
	private Context context;

	public ImageCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, String imagePath) {
		super(context, layout, c, from, to);
		this.c = c;
		this.imagePath = imagePath;
		this.context = context;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		View retView = super.getView(position, v, parent);
		
		c.moveToPosition(position);
		String isbn = c.getString(1);
		
		ImageView iv = (ImageView) retView.findViewById(R.id.cover);
		File file = new File(imagePath,isbn+".jpg");
		Bitmap cover;
		if(file.exists()){
			cover = BitmapFactory.decodeFile(file.getAbsolutePath());
		}else{
			cover = BitmapFactory.decodeResource(context.getResources(), R.drawable.unknown);
		}
		iv.setImageBitmap(cover);
		return retView;
	}

}
