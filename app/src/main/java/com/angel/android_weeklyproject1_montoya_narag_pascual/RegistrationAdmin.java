package com.angel.android_weeklyproject1_montoya_narag_pascual;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationAdmin extends AppCompatActivity {

    DatabaseHelper helper;
    SQLiteDatabase db;
    EditText etIDNumber, etPassword, etConfirmPass, etLastname, etFirstname;
    AlertDialogManager alert = new AlertDialogManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_admin);
        etIDNumber = (EditText) findViewById(R.id.etIDNumRegAdmin);
        etPassword = (EditText) findViewById(R.id.etPasswordRegAdmin);
        etConfirmPass = (EditText) findViewById(R.id.etConfirmPassAdmin);
        etLastname = (EditText) findViewById(R.id.etLastnameAdminReg);
        etFirstname = (EditText) findViewById(R.id.etFirstnameAdminReg);
        helper = new DatabaseHelper(this);
    }

    public void registerAccountAdmin(View view) {
        if (etIDNumber.getText().toString().trim().equals("") || etPassword.getText().toString().trim().equals("") ||
                etConfirmPass.getText().toString().trim().equals("") || etLastname.getText().toString().trim().equals("") ||
                etFirstname.getText().toString().trim().equals("")) {
            alert.showAlertDialog(RegistrationAdmin.this, "Registration failed", "Please fill up all fields", false);
            return;
        }
        if (!etPassword.getText().toString().equals(etConfirmPass.getText().toString())) {
            alert.showAlertDialog(RegistrationAdmin.this, "Registration failed", "Passwords does not match", false);
            return;
        } else {
            boolean check = helper.isRecordExistingAdmin(etIDNumber.getText().toString());
            boolean insert = helper.insertDataAdminReg(etIDNumber.getText().toString(), etLastname.getText().toString(),
                    etFirstname.getText().toString(), etPassword.getText().toString());
            if(check){
                alert.showAlertDialog(RegistrationAdmin.this, "Registration failed", "ID Number already exists", false);
                return;
            } else {
                if (insert) {
                    Toast.makeText(this, "Account Saved", Toast.LENGTH_LONG).show();
                    Intent in = new Intent(this, LoginAdmin.class);
                    startActivity(in);
                } else
                    Toast.makeText(this, "Account not Saved", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void backToRegisterShell(View view){
        Intent in = new Intent(this, RegisterShell.class);
        startActivity(in);
    }
}
