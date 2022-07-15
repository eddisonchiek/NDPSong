package sg.edu.sg.c346.id21034014.ndpsongs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Song.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SONG = "SONG";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "TITLE";
    private static final String COLUMN_SINGER = "SINGER";
    private static final String COLUMN_YEAR = "YEAR";
    private static final String COLUMN_STARS = "STARS";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_SONG + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + "TEXT , "
                + COLUMN_SINGER + "TEXT , "
                + COLUMN_YEAR + "INTEGER , "
                + COLUMN_STARS + " INTEGER ) ";

        db.execSQL(createNoteTableSql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE " + TABLE_SONG + " ADD COLUMN  module_name TEXT ");
    }
    public long insertNote(String TITLE, String SINGER, int YEAR, String STARS) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, TITLE);
        values.put(COLUMN_SINGER, SINGER);
        values.put(COLUMN_YEAR, YEAR);
        values.put(COLUMN_STARS, STARS);



        long result = db.insert(TABLE_SONG, null, values);
        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1
        return result;
    }
    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> notes = new ArrayList<Note>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns= {COLUMN_ID, COLUMN_TITLE,COLUMN_SINGER,COLUMN_YEAR,COLUMN_STARS};
        Cursor cursor = db.query(TABLE_SONG, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String noteContent = cursor.getString(1);
                Note note = new Note(id, noteContent);
                notes.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }
    public int updateNote(Note data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getNoteContent());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_SONG, values, condition, args);
        db.close();
        return result;
    }
    public int deleteNote(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONG, condition, args);
        db.close();
        return result;
    }
    public ArrayList<Note> getAllNotes(String keyword) {
        ArrayList<Note> notes = new ArrayList<Note>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_TITLE,COLUMN_SINGER,COLUMN_YEAR,COLUMN_STARS};
        String condition = COLUMN_TITLE + " Like ?";
        String[] args = { "%" +  keyword + "%"};
        Cursor cursor = db.query(TABLE_SONG, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String noteContent = cursor.getString(1);
                Note note = new Note(id, noteContent);
                notes.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }






}

