package com.angel.android_weeklyproject1_montoya_narag_pascual;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class WelcomePageAdmin extends AppCompatActivity {

    SessionManager session;
    TextView tvID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page_admin);
        session = new SessionManager(getApplicationContext());

        HashMap<String, String> user = session.getUserDetails();
        tvID = (TextView) findViewById(R.id.tvDisplayAdminNum);
        SharedPreferences sp = getSharedPreferences("MyAdminID", Context.MODE_PRIVATE);
        String idNum = sp.getString("adminID", "0000000000");
        tvID.setText(idNum);
    }

    public void onClickWelcomePageAdmin(View view) {
        switch (view.getId()) {
            case R.id.btViewReservationsAdmin:
                viewAllReservations();
                break;
            case R.id.btLogoutAdmin:
                logoutAdmin();
                break;
        }
    }

    public void viewAllReservations() {
        Intent in = new Intent(this, AdminViewReservation.class);
        startActivity(in);
    }

    public void logoutAdmin(){
        session.logoutUser();
    }
}
