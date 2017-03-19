package com.angel.android_weeklyproject1_montoya_narag_pascual;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btLogin, btRegister, btSignIn;
    EditText userID, userPass, idNumStudNum, pass;
    DatabaseHelper helper;
    AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new SessionManager(getApplicationContext());

        btLogin=(Button)findViewById(R.id.btLogin);
        btSignIn = (Button) findViewById(R.id.btNextToWelcomePage);
        btRegister=(Button)findViewById(R.id.btRegister);
        userID = (EditText) findViewById(R.id.etLoginIDStudNum);
        userPass = (EditText) findViewById(R.id.etPasswordLogin);
        idNumStudNum = (EditText) findViewById(R.id.etLoginIDStudNum);
        pass = (EditText) findViewById(R.id.etPasswordLogin);
        helper = new DatabaseHelper(this);

        HashMap<String, String> user = session.getUserDetails();
        String userID = user.get(SessionManager.KEY_ID);
        String pass = user.get(SessionManager.KEY_PASS);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btRegister:
                registerShell();
                break;
            case R.id.btLogin:
                loginPage();
                break;
        }
    }

    public void registerShell(){
        Intent in = new Intent(this, RegisterShell.class);
        startActivity(in);
    }

    public void loginPage() {
        Intent in = new Intent(this, LoginShell.class);
        startActivity(in);
    }
}
