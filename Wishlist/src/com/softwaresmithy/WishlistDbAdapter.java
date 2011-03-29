package com.softwaresmithy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcelable.Creator;
import android.util.Log;

public class WishlistDbAdapter {
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;
	
	private static final String DATABASE_NAME="softwaresmithy";
	private static final String DATABASE_TABLE="wishlist";
	
	public static final String COL_ID="_id";
	public static final String COL_ISBN="isbn";
	public static final String COL_PIC="pic_url";
	public static final String COL_TITLE="title";
	public static final String COL_AUTHOR="author";
	public static final String COL_STATE="state";
	public static final String COL_DUE_DATE="due_date";
	public static final String COL_FOUND="found";
	public static final String COL_CLOSED="closed_date";
	
	public static final String[] allColumns = new String[]{COL_ID, COL_ISBN, COL_PIC, COL_TITLE, COL_AUTHOR, COL_STATE, COL_DUE_DATE, COL_FOUND, COL_CLOSED};
	
	private final Context mCtx;
	
	private static final String DATABASE_CREATE="create table "+DATABASE_TABLE+" (" +
			COL_ID+ " integer primary key autoincrement, " +
			COL_ISBN + " text not null, " +
			COL_PIC + " text, " +
			COL_TITLE + " text, " +
			COL_AUTHOR + " text, " +
			COL_STATE + " text, " +
			COL_DUE_DATE + " integer, " +	//unix time
			COL_FOUND + " integer, " + 	//boolean
			COL_CLOSED + " integer "+	//unix time
			");";
	private static final int DATABASE_VERSION = 2;
	
	private static class DatabaseHelper extends SQLiteOpenHelper{
		public DatabaseHelper(Context context){
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
			ContentValues i = new ContentValues();
			i.put(COL_ISBN, "9780765315151");
			i.put(COL_TITLE, "Up Against It");
			i.put(COL_AUTHOR, "M. J. Locke");
			db.insert(DATABASE_TABLE, null, i);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("WishlistDbAdapter", "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
            onCreate(db);
		}
	}
	public long createItem(String isbn, String pic, String title, String author, String state){
		ContentValues i = new ContentValues();
		i.put(COL_ISBN, isbn);
		i.put(COL_PIC, pic);
		i.put(COL_TITLE, title);
		i.put(COL_AUTHOR, author);
		i.put(COL_STATE, state);
		
		return mDb.insert(DATABASE_TABLE, null, i);
	}
	
	public Cursor readItem(long id){
		Cursor c= mDb.query(DATABASE_TABLE, allColumns, COL_ID + "=?",new String[]{Long.toString(id)}, null, null, null);
		if(c != null){
			c.moveToFirst();
		}
		return c;
	}
	public boolean updateItem(long id, String isbn, String pic, String title, String author, String state, String dueDate, String found, String closed){
		ContentValues i = new ContentValues();
		i.put(COL_ISBN, isbn);
		i.put(COL_PIC, pic);
		i.put(COL_TITLE, title);
		i.put(COL_AUTHOR, author);
		i.put(COL_STATE, state);
		i.put(COL_DUE_DATE, dueDate);
		i.put(COL_FOUND, found);
		i.put(COL_CLOSED, closed);
		
		return mDb.update(DATABASE_TABLE, i, COL_ID + "=?",new String[]{Long.toString(id)}) > 0;
	}
	
	public boolean deleteItem(long id){
		return mDb.delete(DATABASE_TABLE, COL_ID + "=?",new String[]{Long.toString(id)}) > 0;
	}
	public Cursor getAll(){
		return mDb.query(DATABASE_TABLE, allColumns, null, null, null, null, null);
	}
	public WishlistDbAdapter(Context ctx){
		this.mCtx = ctx;
	}
	
	public WishlistDbAdapter open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		mDbHelper.close();
	}
}
