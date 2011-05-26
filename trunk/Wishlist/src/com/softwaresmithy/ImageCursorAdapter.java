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

/**
 * A custom cursor adapter to generate item entries for each of the objects in the passed cursor.
 * In this case, it makes sure the images are retrieved from the file system and displayed
 * appropriately.
 * @author SZY4ZQ
 *
 */
public class ImageCursorAdapter extends SimpleCursorAdapter implements Filterable{
	/**
	 * A cursor containing the DB rows to be displayed on the UI.
	 */
	private Cursor c;
	/**
	 * The folder on the filesystem containing images to be displayed.
	 * TODO: Should this be retrieved from the context instead?
	 */
	private String imagePath;
	/**
	 * The larger application context.
	 */
	private Context context;
	/**
	 * Database accessor.
	 */
	private WishlistDbAdapter mDbHelper;

	/**
	 * 
	 * @param context application context
	 * @param mDbHelper database accessor
	 * @param layout pointer to target layout object
	 * @param c list of db rows to display
	 * @param from db column names
	 * @param to layout view IDs
	 * @param imagePath path to image directory
	 */
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
			
			case AVAILABLE:
				state.setBackgroundColor(context.getResources().getColor(R.color.status_available));
				break;
			case SHORT_WAIT:
				state.setBackgroundColor(context.getResources().getColor(R.color.status_short_wait));
				break;
			case WAIT:
				state.setBackgroundColor(context.getResources().getColor(R.color.status_wait));
				break;
			case LONG_WAIT:
				state.setBackgroundColor(context.getResources().getColor(R.color.status_long_wait));
				break;
			case NO_MATCH:
			default:
				state.setBackgroundColor(context.getResources().getColor(R.color.status_default));
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
	
	/**
	 * This will return a cursor containing only those elements intended to display based on the passed CSV list of statuses to hide.
	 * @param constraint comma separated list of LibStatus.STATUS enums to hide from view
	 * @return cursor limiting data shown according to status
	 */
	public Cursor filterByStatus(CharSequence constraint){
		if(constraint == null){
			return mDbHelper.getAll();
		}else{
			return mDbHelper.filterByStatus(constraint);
		}		
	}
}
