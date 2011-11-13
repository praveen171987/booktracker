package com.softwaresmithy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.softwaresmithy.library.AndroidLibStatus;

import java.util.Collection;
import java.util.Date;

public class WishlistDbAdapter {
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "softwaresmithy";
    private static final String DATABASE_TABLE = "wishlist";

    public static final String COL_ID = "_id";
    public static final String COL_ISBN = "isbn";
    public static final String COL_VOLUME_ID = "volume_id";
    public static final String COL_TITLE = "title";
    public static final String COL_AUTHOR = "author";
    public static final String COL_PUB_DATE = "pub_date";
    public static final String COL_ADD_DATE = "add_date";
    public static final String COL_DUE_DATE = "due_date";
    public static final String COL_CLOSED_DATE = "closed_date";
    public static final String COL_STATE = "state";

    public static final String[] ALL_COLUMNS = new String[]{COL_ID, COL_ISBN, COL_VOLUME_ID, COL_TITLE, COL_AUTHOR, COL_PUB_DATE, COL_ADD_DATE, COL_DUE_DATE, COL_CLOSED_DATE, COL_STATE};

    private final Context mCtx;

    private static final String DATABASE_CREATE = "create table " + DATABASE_TABLE + " (" +
            COL_ID + " integer primary key autoincrement, " +
            COL_ISBN + " text not null, " +
            COL_VOLUME_ID + " text, " +
            COL_TITLE + " text, " +
            COL_AUTHOR + " text, " +
            COL_PUB_DATE + " integer, " +    //unix time
            COL_ADD_DATE + " integer, " +    //unix time
            COL_DUE_DATE + " integer, " +    //unix time
            COL_CLOSED_DATE + " integer, " +    //unix time
            COL_STATE + " text " +
            ");";
    private static final int DATABASE_VERSION = 3;

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("WishlistDbAdapter", "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public long createItem(BookJB b) {
        ContentValues i = new ContentValues();
        i.put(COL_ISBN, b.getIsbn());
        i.put(COL_VOLUME_ID, b.getVolumeId());
        i.put(COL_TITLE, b.getTitle());
        i.put(COL_AUTHOR, b.getAuthor());
        if (b.getPubDate() != null) {
            i.put(COL_PUB_DATE, b.getPubDate().getTime());
        }
        if (b.getAddDate() != null) {
            i.put(COL_ADD_DATE, b.getAddDate().getTime());
        } else {
            i.put(COL_ADD_DATE, new Date().getTime());
        }
        if (b.getDueDate() != null) {
            i.put(COL_DUE_DATE, b.getDueDate().getTime());
        }
        if (b.getClosedDate() != null) {
            i.put(COL_CLOSED_DATE, b.getClosedDate().getTime());
        }
        i.put(COL_STATE, b.getState());

        return mDb.insert(DATABASE_TABLE, null, i);
    }

    public Cursor readItem(long id) {
        Cursor c = mDb.query(DATABASE_TABLE, ALL_COLUMNS, COL_ID + "=?", new String[]{Long.toString(id)}, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public BookJB readItemByIsbn(String isbn) {
        Cursor c = mDb.query(DATABASE_TABLE, ALL_COLUMNS, COL_ISBN + "=?", new String[]{isbn}, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return cursorToBookJB(c);
    }

    public BookJB getItemFromCursor(Cursor c) {
        return cursorToBookJB(c);
    }

    private BookJB cursorToBookJB(Cursor c) {
        BookJB retVal = new BookJB();
        retVal.set_id(c.getLong(c.getColumnIndexOrThrow(COL_ID)));
        retVal.setIsbn(c.getString(c.getColumnIndexOrThrow(COL_ISBN)));
        retVal.setVolumeId(c.getString(c.getColumnIndexOrThrow(COL_VOLUME_ID)));
        retVal.setTitle(c.getString(c.getColumnIndexOrThrow(COL_TITLE)));
        retVal.setAuthor(c.getString(c.getColumnIndexOrThrow(COL_AUTHOR)));
        retVal.setPubDate(new Date(c.getLong(c.getColumnIndexOrThrow(COL_PUB_DATE))));
        retVal.setAddDate(new Date(c.getLong(c.getColumnIndexOrThrow(COL_ADD_DATE))));
        retVal.setDueDate(new Date(c.getLong(c.getColumnIndexOrThrow(COL_DUE_DATE))));
        retVal.setClosedDate(new Date(c.getLong(c.getColumnIndexOrThrow(COL_CLOSED_DATE))));
        retVal.setState(c.getString(c.getColumnIndexOrThrow(COL_STATE)));

        return retVal;
    }

    public boolean updateItem(BookJB b) {
        ContentValues i = new ContentValues();
        i.put(COL_ISBN, b.getIsbn());
        i.put(COL_VOLUME_ID, b.getVolumeId());
        i.put(COL_TITLE, b.getTitle());
        i.put(COL_AUTHOR, b.getAuthor());
        if (b.getPubDate() != null) {
            i.put(COL_PUB_DATE, b.getPubDate().getTime());
        }
        if (b.getAddDate() != null) {
            i.put(COL_ADD_DATE, b.getAddDate().getTime());
        } else {
            i.put(COL_ADD_DATE, new Date().getTime());
        }
        if (b.getDueDate() != null) {
            i.put(COL_DUE_DATE, b.getDueDate().getTime());
        }
        if (b.getClosedDate() != null) {
            i.put(COL_CLOSED_DATE, b.getClosedDate().getTime());
        }
        i.put(COL_STATE, b.getState());

        return mDb.update(DATABASE_TABLE, i, COL_ID + "=?", new String[]{Long.toString(b.get_id())}) > 0;
    }

    public boolean deleteItem(long id) {
        return mDb.delete(DATABASE_TABLE, COL_ID + "=?", new String[]{Long.toString(id)}) > 0;
    }

    public Cursor getAll() {
        return mDb.query(DATABASE_TABLE, ALL_COLUMNS, null, null, null, null, null);
    }

    public WishlistDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public WishlistDbAdapter open() {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public Cursor filterByStatus(Collection<AndroidLibStatus.STATUS> constraints) {
        String statuses = "'" + join(constraints, "','") + "'";
        return mDb.query(DATABASE_TABLE, ALL_COLUMNS, COL_STATE + " NOT IN (" + statuses + ")", null, null, null, null);
    }

    private String join(Collection<AndroidLibStatus.STATUS> constraints, String concatenation) {
        StringBuilder retVal = new StringBuilder();
        boolean first = true;
        for (AndroidLibStatus.STATUS constraint : constraints) {
            if (first) {
                first = false;
            } else {
                retVal.append(concatenation);
            }
            retVal.append(constraint);
        }
        return retVal.toString();
    }

    public boolean resetState() {
        ContentValues nullState = new ContentValues();
        nullState.putNull(COL_STATE);
        int numRows = mDb.update(DATABASE_TABLE, nullState, null, null);
        return numRows > 0;
    }
}
