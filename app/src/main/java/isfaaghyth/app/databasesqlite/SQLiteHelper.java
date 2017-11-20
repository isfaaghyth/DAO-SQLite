package isfaaghyth.app.databasesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isfaaghyth on 11/17/17.
 * github: @isfaaghyth
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLiteHelper";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "books";
    private static final String TABLE_NOTE = "book_author";

    private static final String ID = "_id";
    private static final String TITLE = "title";
    private static final String AUTHOR = "author";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NOTE + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + TITLE + " TEXT,"
                + AUTHOR + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + TABLE_NOTE);
        onCreate(sqLiteDatabase);
    }

    public void insertData(BookModel data) {
        SQLiteDatabase db = this.getWritableDatabase();

        if (!isDataExist(data.get_id())) {
            ContentValues values = new ContentValues();
            values.put(ID, data.get_id());
            values.put(TITLE, data.get_id());
            values.put(AUTHOR, data.getAuthor());

            db.insert(TABLE_NOTE, null, values);
        } else {
            try {
                db.beginTransaction();
                db.execSQL("UPDATE " + TABLE_NOTE +
                        " SET title='" + data.getTitle() + "', " +
                        "author='" + data.getAuthor() + "' " +
                        "WHERE _id='" + data.get_id() + "'");
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        }

        db.close();
    }

    public List<BookModel> getData() {
        List<BookModel> books = new ArrayList<>();
        String USER_DETAIL_SELECT_QUERY = "SELECT * FROM " + TABLE_NOTE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(USER_DETAIL_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    books.add(new BookModel()
                                .set_id(Integer.valueOf(cursor.getString(cursor.getColumnIndex(ID))))
                                .setTitle(cursor.getString(cursor.getColumnIndex(TITLE)))
                                .setAuthor(cursor.getString(cursor.getColumnIndex(AUTHOR))));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "error bro");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return books;
    }

    private boolean isDataExist(int _id) {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String Query = "SELECT * FROM " + TABLE_NOTE + " WHERE _id='" + _id + "'";
        Cursor cursor = sqldb.rawQuery(Query, null);
        if(cursor.getCount() <= 0) {
            cursor.close();
            return false;
        } else {
            cursor.close();
            return true;
        }
    }

    public void removeDataById(int _id) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction();
            db.execSQL("DELETE from " + TABLE_NOTE + " WHERE _id ='" + _id + "'");
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.d(TAG, "Error while trying to delete book");
        } finally {
            db.endTransaction();
        }
    }
}
