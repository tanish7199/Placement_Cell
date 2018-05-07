package com.gmail.placement_cell.database;

import android.net.Uri;
import android.provider.BaseColumns;

public final class timetableContract {

    public static final String CONTENT_AUTHORITY = "com.gmail.placement_cell";
    public static final String TABLE_NAME = "timetable";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, TABLE_NAME);


    private timetableContract() {
    }

    public static final class timetableEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, TABLE_NAME);
        public static final String _ID = BaseColumns._ID;
        public static final String TEACHER_NAME = "teacher_name";
        public static final String MONDAY = "monday";
        public static final String TUESDAY = "tuesday";
        public static final String WEDNESDAY = "wednesday";
        public static final String THURSDAY = "thursday";
        public static final String FRIDAY = "friday";
    }

}
