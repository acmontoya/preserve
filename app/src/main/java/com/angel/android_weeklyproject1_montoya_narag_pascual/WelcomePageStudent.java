package com.angel.android_weeklyproject1_montoya_narag_pascual;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class WelcomePageStudent extends AppCompatActivity {

    SessionManager session;
    SharedPreferences pref;
    Context _context;
    DatabaseHelper helper;
    TextView tvId;
    public static final String KEY_ID = "userID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page_student);
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        tvId = (TextView) findViewById(R.id.tvDisplayStudNum);
        SharedPreferences sp = getSharedPreferences("MyUserID", Context.MODE_PRIVATE);
        String idNum = sp.getString("userID", "0000000000");
        tvId.setText(idNum);
    }

    public void onClickWelcomePageStudent(View view) {
        switch (view.getId()) {
            case R.id.btCreateReservationStudent:
                placeReservation();
                break;
            case R.id.btCheckStudReservation:
                checkReservation();
                break;
            case R.id.btLogoutStudent:
                logoutStudent();
                break;
        }
    }

    public void logoutStudent(){
        session.logoutUser();
    }

    public void placeReservation(){
        Intent in = new Intent(this, StudentReservation.class);
        String id = getIntent().getStringExtra("userId");
        in.putExtra("userId",id);
        startActivity(in);
    }

    public void checkReservation(){
        Intent in = new Intent(this, ViewReservationStudent.class);
        startActivity(in);
        
    }
}
