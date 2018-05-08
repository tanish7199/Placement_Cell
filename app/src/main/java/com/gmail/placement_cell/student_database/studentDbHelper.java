package com.gmail.placement_cell.student_database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class studentDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "student.db";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + StudentContract.TABLE_NAME +
            "( " + StudentContract.StudentEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            StudentContract.StudentEntry.STUDENT_NAME + " TEXT NOT NULL, " +
            StudentContract.StudentEntry.ROLL_NUMBER + " INTEGER, " +
            StudentContract.StudentEntry.CGPA + " TEXT, " +
            StudentContract.StudentEntry.RESUME + " TEXT " +
            ");";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + StudentContract.TABLE_NAME;

    /*public timetableDbHelper(View.OnClickListener context) {
        super((Context) context, DATABASE_NAME, null, DATABASE_VERSION);
    }*/

    public studentDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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