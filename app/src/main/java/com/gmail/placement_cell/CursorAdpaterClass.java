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
        super(context, c,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView t1 = view.findViewById(R.id.t1);
        TextView t2 = view.findViewById(R.id.t2);
        t1.setText(cursor.getString(cursor.getColumnIndex(timetableEntry.TEACHER_NAME)));
        t2.setText(cursor.getString(cursor.getColumnIndex(timetableEntry.MONDAY)));
    }
}
