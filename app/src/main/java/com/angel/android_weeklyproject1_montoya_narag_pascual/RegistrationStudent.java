package com.angel.android_weeklyproject1_montoya_narag_pascual;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationStudent extends AppCompatActivity {

    DatabaseHelper helper;
    EditText etStudNumber, etPasswordStud, etConfirmPassStud, etLastnameStud, etFirstnameStud, etCourseStud;
    AlertDialogManager alert = new AlertDialogManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_student);
        helper = new DatabaseHelper(this);
        etStudNumber = (EditText) findViewById(R.id.etStudNumReg);
        etPasswordStud = (EditText) findViewById(R.id.etPasswordStudReg);
        etConfirmPassStud = (EditText) findViewById(R.id.etConfirmPassStud);
        etLastnameStud = (EditText) findViewById(R.id.etStudLastnameReg);
        etFirstnameStud = (EditText) findViewById(R.id.etStudFirstnameReg);
        etCourseStud = (EditText) findViewById(R.id.etCourseStudReg);
    }

    public void registerAccountStudent(View view) {

        if(etStudNumber.getText().toString().trim().equals("")||etPasswordStud.getText().toString().trim().equals("")||
                etConfirmPassStud.getText().toString().trim().equals("")||etLastnameStud.getText().toString().trim().equals("")||
                etFirstnameStud.getText().toString().trim().equals("")) {
            alert.showAlertDialog(RegistrationStudent.this, "Registration failed", "Please fill up all fields", false);
            return;
        }
        if(!etPasswordStud.getText().toString().equals(etConfirmPassStud.getText().toString())) {
            alert.showAlertDialog(RegistrationStudent.this, "Registration failed", "Passwords does not match", false);
            return;
        } else {
            String fullname;
            fullname = etLastnameStud.getText().toString() + " " + etFirstnameStud.getText().toString();
            SharedPreferences sp = getSharedPreferences("MyUserName", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("userFullname", fullname);
            editor.commit();
            boolean check = helper.isRecordExistingStudent(etStudNumber.getText().toString());
            boolean insert = helper.insertDataStudentReg(etStudNumber.getText().toString(), etLastnameStud.getText().toString(),
                    etFirstnameStud.getText().toString(), etCourseStud.getText().toString(), etPasswordStud.getText().toString());
            if(check){
                alert.showAlertDialog(RegistrationStudent.this, "Registration failed", "Student number already exists", false);
                return;
            } else {
                if (insert) {
                    Toast.makeText(this, "Account Saved", Toast.LENGTH_LONG).show();
                    Intent in = new Intent(this, LoginStudent.class);
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
