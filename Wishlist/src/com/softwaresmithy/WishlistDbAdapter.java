package com.softwaresmithy;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class WishlistDbAdapter {
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;
	
	private static final String DATABASE_NAME="softwaresmithy";
	private static final String DATABASE_TABLE="wishlist";
	
	private static final String COL_ID="_id";
	private static final String COL_ISBN="isbn";
	private static final String COL_PIC="pic_url";
	private static final String COL_TITLE="title";
	private static final String COL_AUTHOR="author";
	private static final String COL_STATE="state";
	private static final String COL_DUE_DATE="due_date";
	private static final String COL_FOUND="found";
	private static final String COL_CLOSED="closed_date";
	
	public static final String[] allColumns = new String[]{COL_ID, COL_ISBN, COL_PIC, COL_TITLE, COL_AUTHOR, COL_STATE, COL_DUE_DATE, COL_FOUND, COL_CLOSED};
	
	private final Context mCtx;
	
	private static final String DATABASE_CREATE="create table "+DATABASE_TABLE+" (" +
			COL_ID+ " integer primary key autoincrement " +
			COL_ISBN + "text not null" +
			COL_PIC + "text " +
			COL_TITLE + "text " +
			COL_AUTHOR + "text " +
			COL_STATE + "text " +
			COL_DUE_DATE + "integer " +	//unix time
			COL_FOUND + "integer " + 	//boolean
			COL_CLOSED + "integer "+	//unix time
			");";
	private static final int DATABASE_VERSION = 2;
	
	private static class DatabaseHelper extends SQLiteOpenHelper{
		public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
			super(context, DATABASE_NAME, factory, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("WishlistDbAdapter", "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
            onCreate(db);
		}
	}
	
	public Cursor getAll(){
		return mDb.query(DATABASE_TABLE, allColumns, null, null, null, null, null);
	}
	public WishlistDbAdapter(Context ctx){
		this.mCtx = ctx;
	}
	
	public WishlistDbAdapter open() throws SQLException {
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		mDbHelper.close();
	}
}
