package com.gmail.placement_cell.student_database;

import android.net.Uri;
import android.provider.BaseColumns;

public class StudentContract {
    public static final String CONTENT_AUTHORITY = "com.gmail.placement_cell.student";
    public static final String TABLE_NAME = "student";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, TABLE_NAME);


    private StudentContract() {
    }

    public static final class StudentEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, TABLE_NAME);
        public static final String _ID = BaseColumns._ID;
        public static final String STUDENT_NAME = "student_name";
        public static final String ROLL_NUMBER = "roll_no";
        public static final String CGPA = "cgpa";
        public static final String RESUME = "resume";
    }

}

