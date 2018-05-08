package com.gmail.placement_cell;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.gmail.placement_cell.student_database.StudentContract;

public class CursorAdapterClassStudent extends CursorAdapter {

    CursorAdapterClassStudent(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_student, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView t1 = view.findViewById(R.id.showStudentName);
        TextView t2 = view.findViewById(R.id.showCGPA);
        TextView t3 = view.findViewById(R.id.showRollNumber);
        TextView t4 = view.findViewById(R.id.showResume);

        t1.setText(cursor.getString(cursor.getColumnIndex(StudentContract.StudentEntry.STUDENT_NAME)));
        t2.setText(cursor.getString(cursor.getColumnIndex(StudentContract.StudentEntry.CGPA)));
        t3.setText(cursor.getString(cursor.getColumnIndex(StudentContract.StudentEntry.ROLL_NUMBER)));
        t4.setText(cursor.getString(cursor.getColumnIndex(StudentContract.StudentEntry.RESUME)));

    }
}
