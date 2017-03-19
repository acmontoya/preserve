package com.angel.android_weeklyproject1_montoya_narag_pascual;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class UserCursorAdapter extends CursorAdapter
{
    public UserCursorAdapter(Context context, Cursor cursor)
    {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        return LayoutInflater.from(context).inflate(R.layout.record_template, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        TextView timeView = (TextView)view.findViewById(R.id.tvTimeFrom_Temp);
        TextView roomView = (TextView)view.findViewById(R.id.tvRoom_Temp);
        TextView sectionView = (TextView)view.findViewById(R.id.tvSection_Temp);

        timeView.setText(cursor.getString(cursor.getColumnIndexOrThrow(StudentReservationDB.StudentDetails.STUDENT_RESERVE_COL3)));
        roomView.setText(cursor.getString(cursor.getColumnIndexOrThrow(StudentReservationDB.StudentDetails.STUDENT_RESERVE_COL4)));
        sectionView.setText(cursor.getString(cursor.getColumnIndexOrThrow(StudentReservationDB.StudentDetails.STUDENT_RESERVE_COL5)));
    }
}
