package com.angel.android_weeklyproject1_montoya_narag_pascual;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginAdmin extends AppCompatActivity {

    EditText idNumStudNum, pass;
    DatabaseHelper helper;
    Button btLogin, btBack;
    SessionManager session;
    AlertDialogManager alert = new AlertDialogManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        idNumStudNum = (EditText) findViewById(R.id.etLoginIDStudNum);
        pass = (EditText) findViewById(R.id.etPasswordLogin);
        btLogin = (Button) findViewById(R.id.btNextToWelcomePage);
        btBack = (Button) findViewById(R.id.btBackToMain);
        session = new SessionManager(getApplicationContext());
        helper = new DatabaseHelper(this);
    }

    public void onClickLogin(View view) {
        switch (view.getId()) {
            case R.id.btNextToWelcomePage:
                signIn();
                break;
            case R.id.btBackToMain:
                backToMain();
                break;
        }
    }

    public void signIn() {
        SharedPreferences sp = getSharedPreferences("MyAdminID", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("adminID", idNumStudNum.getText().toString());
        editor.commit();

        if(idNumStudNum.getText().toString().trim().equals("")||pass.getText().toString().trim().equals("")) {
            alert.showAlertDialog(LoginAdmin.this, "Login failed", "Please fill up all fields", false);
            return;
        }
        String idNum = idNumStudNum.getText().toString();
        String password = pass.getText().toString();
        String storedPassword = helper.getUserAccount(idNum);

        if(password.equals(storedPassword)) {
            session.createLoginSession(idNum, password);
            Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show();
            Intent in = new Intent(this, WelcomePageAdmin.class);
            in.putExtra("userId",idNum);
            startActivity(in);
        } else {
            alert.showAlertDialog(LoginAdmin.this, "Login failed", "ID Number/Password is Incorrect", false);
        }
    }

    public void backToMain(){
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }
}

