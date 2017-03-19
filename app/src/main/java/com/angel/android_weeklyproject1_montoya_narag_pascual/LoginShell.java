package com.angel.android_weeklyproject1_montoya_narag_pascual;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginShell extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_shell);
    }

    public void onClickLoginShell(View view) {
        switch (view.getId()) {
            case R.id.btLoginShellAdmin:
                loginForAdmin();
                break;
            case R.id.btLoginShellStudent:
                loginForStudent();
                break;
            case R.id.btBackToMain:
                backToMain();
                break;
        }
    }

    private void loginForStudent() {
        Intent in = new Intent(this, LoginStudent.class);
        startActivity(in);
    }

    private void loginForAdmin() {
        Intent in = new Intent(this, LoginAdmin.class);
        startActivity(in);
    }


    public void backToMain(){
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }
}
