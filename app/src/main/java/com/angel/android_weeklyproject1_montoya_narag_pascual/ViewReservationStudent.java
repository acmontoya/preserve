package com.angel.android_weeklyproject1_montoya_narag_pascual;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

public class ViewReservationStudent extends AppCompatActivity {

    DatabaseHelper helper = null;
    SQLiteDatabase _database = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservation_student);

        try
        {
            //Creates the db upon instantiation (executes on create)
            helper = new DatabaseHelper(getApplicationContext());
            _database = helper.getReadableDatabase();
            SharedPreferences sp = getSharedPreferences("MyUserID", Context.MODE_PRIVATE);
            String idNum = sp.getString("userID", "0000000000");

            //Retrieve records
            Cursor cursor = _database.rawQuery("SELECT * FROM " +
                    StudentReservationDB.StudentDetails.TABLE4 + " WHERE " +
                    StudentReservationDB.StudentDetails.STUDENT_RESERVE_COL2 + " = " + idNum, null);
            if(cursor.getCount() > 0)
            {
                //Attach to list view
                ListView userListView = (ListView) findViewById(R.id.listViewReservation);
                UserCursorAdapter adapter = new UserCursorAdapter(this, cursor);
                userListView.setAdapter(adapter);
            }

        }
        catch(Exception ex)
        {
            Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
            startActivity(new Intent(this, WelcomePageStudent.class));
        }
    }

}
