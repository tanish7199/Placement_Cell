package com.gmail.placement_cell;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.gmail.placement_cell.database.timetableContract.timetableEntry;

public class CursorAdpaterClass extends CursorAdapter {
    CursorAdpaterClass(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView t1 = view.findViewById(R.id.showTeacherName);
        TextView t2 = view.findViewById(R.id.showMonday);
        TextView t3 = view.findViewById(R.id.showTuesday);
        TextView t4 = view.findViewById(R.id.showWednesday);
        TextView t5 = view.findViewById(R.id.showThursday);
        TextView t6 = view.findViewById(R.id.showFriday);

        t1.setText(cursor.getString(cursor.getColumnIndex(timetableEntry.TEACHER_NAME)));
        t2.setText(cursor.getString(cursor.getColumnIndex(timetableEntry.MONDAY)));
        t3.setText(cursor.getString(cursor.getColumnIndex(timetableEntry.TUESDAY)));
        t4.setText(cursor.getString(cursor.getColumnIndex(timetableEntry.WEDNESDAY)));
        t5.setText(cursor.getString(cursor.getColumnIndex(timetableEntry.THURSDAY)));
        t6.setText(cursor.getString(cursor.getColumnIndex(timetableEntry.FRIDAY)));

    }
}
