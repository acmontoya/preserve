package com.angel.android_weeklyproject1_montoya_narag_pascual;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginStudent extends AppCompatActivity {

    EditText studNum, pass;
    DatabaseHelper helper;
    Button btLogin, btBack;
    SessionManager session;
    AlertDialogManager alert = new AlertDialogManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_student);
        studNum = (EditText) findViewById(R.id.etStudNumLogin);
        pass = (EditText) findViewById(R.id.etStudPassLogin);
        btLogin = (Button) findViewById(R.id.btNextToWelcomeStudent);
        btBack = (Button) findViewById(R.id.btBackToMain);
        session = new SessionManager(getApplicationContext());
        helper = new DatabaseHelper(this);
    }

    public void onClickLoginStudent(View view) {
        switch (view.getId()) {
            case R.id.btNextToWelcomeStudent:
                signInStudent();
                break;
            case R.id.btBackToMain:
                backToMain();
                break;
        }
    }

    public void signInStudent() {
        SharedPreferences sp = getSharedPreferences("MyUserID", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userID", studNum.getText().toString());
        editor.commit();

        if(studNum.getText().toString().trim().equals("")||pass.getText().toString().trim().equals("")) {
            alert.showAlertDialog(LoginStudent.this, "Login failed", "Please fill up all fields", false);
            return;
        }
        String idNum = studNum.getText().toString();
        String password = pass.getText().toString();
        String storedPassword = helper.getUserAccountStudent(idNum);
        String surname = helper.getUserLastNameStudent(idNum);
        String firstname = helper.getUserFirstNameStudent(idNum);

        if(password.equals(storedPassword)) {
            session.createLoginSession(idNum, password);
            Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show();
            Intent in = new Intent(this, WelcomePageStudent.class);
            in.putExtra("userId",idNum);
            in.putExtra("userLastname",surname);
            in.putExtra("userFirstname",firstname);
            startActivity(in);
        } else {
            alert.showAlertDialog(LoginStudent.this, "Login failed", "Student Number/Password is Incorrect", false);
        }
    }

    public void backToMain(){
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }
}
