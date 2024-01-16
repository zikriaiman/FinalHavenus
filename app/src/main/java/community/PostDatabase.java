package community;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class PostDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "postapp.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "allnotes";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_CONTENT = "content";

    public PostDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_CONTENT + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }

    public void insertPost(Post post) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, post.getTitle());
        values.put(COLUMN_CONTENT, post.getContent());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Post> getAllNotes() {
        List<Post> postList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
            String content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT));

            Post post = new Post(id, title, content);
            postList.add(post);
        }

        cursor.close();
        db.close();
        return postList;
    }

    public void updatePost(Post post) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, post.getTitle());
        values.put(COLUMN_CONTENT, post.getContent());

        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(post.getId())};

        db.update(TABLE_NAME, values, whereClause, whereArgs);
        db.close();
    }

    public Post getPostByID(int postID) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(postID)};

        Cursor cursor = db.rawQuery(query, selectionArgs);
        cursor.moveToFirst();

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
        String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
        String content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT));

        cursor.close();
        db.close();

        return new Post(id, title, content);
    }

    public void deletePost(int noteId) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(noteId)};
        db.delete(TABLE_NAME, whereClause, whereArgs);
        db.close();
    }

}

