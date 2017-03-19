package com.angel.android_weeklyproject1_montoya_narag_pascual;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AdminViewReservation extends AppCompatActivity {

    DatabaseHelper helper = null;
    SQLiteDatabase _database = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_reservation);
        final TextView timeView = (TextView)findViewById(R.id.tvTimeFrom_Temp);

        try
        {
            //Creates the db upon instantiation (executes on create)
            helper = new DatabaseHelper(getApplicationContext());
            _database = helper.getReadableDatabase();

            //Retrieve records
            Cursor cursor = _database.rawQuery("SELECT * FROM " +
                    StudentReservationDB.StudentDetails.TABLE4, null);
            if(cursor.getCount() > 0)
            {
                //Attach to list view
                ListView userListView = (ListView) findViewById(R.id.listViewAllReservation);
                UserCursorAdapter adapter = new UserCursorAdapter(this, cursor);
                userListView.setAdapter(adapter);

                userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        _database.delete(StudentReservationDB.StudentDetails.TABLE4, StudentReservationDB.StudentDetails.STUDENT_RESERVE_COL3 + " = ?",
                                new String[]{String.valueOf(timeView)});
                    }
                });
            }

        }
        catch(Exception ex)
        {
            Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
            startActivity(new Intent(this, WelcomePageStudent.class));
        }
    }

    public void remove(long id){
        String string =String.valueOf(id);
        _database.execSQL("DELETE FROM favorite WHERE _id = '" + string + "'");
    }


}
