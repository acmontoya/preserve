package com.angel.android_weeklyproject1_montoya_narag_pascual;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegisterShell extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_shell);
    }

    public void onClickRegShell(View view) {
        switch (view.getId()) {
            case R.id.btRegAdmin:
                registrationForAdmin();
                break;
            case R.id.btRegStudent:
                registrationForStudent();
                break;
            case R.id.btBackToMain:
                backToMain();
                break;
        }
    }

    public void registrationForAdmin(){
        Intent in = new Intent(this, RegistrationAdmin.class);
        startActivity(in);
    }

    public void registrationForStudent(){
        Intent in = new Intent(this, RegistrationStudent.class);
        startActivity(in);
    }

    public void backToMain(){
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }
}
