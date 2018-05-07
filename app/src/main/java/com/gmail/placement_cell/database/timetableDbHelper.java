package com.gmail.placement_cell.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class timetableDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "timetable.db";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + timetableContract.TABLE_NAME +
            "( " + timetableContract.timetableEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            timetableContract.timetableEntry.TEACHER_NAME + " TEXT NOT NULL, " +
            timetableContract.timetableEntry.MONDAY + " TEXT, " +
            timetableContract.timetableEntry.TUESDAY + " TEXT, " +
            timetableContract.timetableEntry.WEDNESDAY + " TEXT, " +
            timetableContract.timetableEntry.THURSDAY + " TEXT, " +
            timetableContract.timetableEntry.FRIDAY + " TEXT"+
            ");";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + timetableContract.TABLE_NAME;

    /*public timetableDbHelper(View.OnClickListener context) {
        super((Context) context, DATABASE_NAME, null, DATABASE_VERSION);
    }*/

    public timetableDbHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
