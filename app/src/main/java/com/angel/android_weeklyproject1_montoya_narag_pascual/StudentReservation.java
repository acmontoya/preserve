package com.angel.android_weeklyproject1_montoya_narag_pascual;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class StudentReservation extends AppCompatActivity {

    TimePicker timeFrom, timeTo;
    EditText etRoom, etSection;
    DatabaseHelper helper;
    Button btBack, btContinue;
    AlertDialogManager alert = new AlertDialogManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_reservation);
        timeFrom = (TimePicker) findViewById(R.id.timeFrom);
        etRoom = (EditText) findViewById(R.id.etRoomReserve);
        etSection = (EditText) findViewById(R.id.etSectionReserve);
        btBack = (Button) findViewById(R.id.btBackToWelcomeStudent);
        btContinue = (Button) findViewById(R.id.btContinueToDialogStud);
        helper = new DatabaseHelper(this);
    }

    public void onClickStudentReservation(View view) {
        switch (view.getId()) {
            case R.id.btContinueToDialogStud:
                triggerDialogReserve();
                break;
            case R.id.btBackToWelcomeStudent:
                backToWelcomeStudent();
                break;
        }
    }

    private void triggerDialogReserve() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Submit Reservation?")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        reservationOfStudent();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void backToWelcomeStudent(){
        Intent in = new Intent(this, WelcomePageStudent.class);
        startActivity(in);
    }

    public void reservationOfStudent(){
        int hour = 0;
        int min = 0;
        if(etRoom.getText().toString().trim().equals("")||etSection.getText().toString().trim().equals("")) {
            alert.showAlertDialog(StudentReservation.this, "Reservation failed", "Please fill up all fields", false);
            return;
        } else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                String id = getIntent().getStringExtra("userId");
                SharedPreferences sp = getSharedPreferences("MyUserID", Context.MODE_PRIVATE);
                String idNum = sp.getString("userID", "0000000000");
                String idStudNum = helper.getStudentNumber(idNum);
                String format;
                hour = timeFrom.getHour();
                min = timeFrom.getMinute();

                if (hour == 0) {
                    hour += 12;
                    format = "AM";
                    boolean insert = helper.insertDataStudentReserve(idStudNum,String.valueOf(hour) + ":" + String.valueOf(min) + " " + format, etRoom.getText().toString(),
                            etSection.getText().toString());
                    if (insert) {
                        Toast.makeText(this, "Reservation Saved", Toast.LENGTH_LONG).show();
                        Intent in = new Intent(this, WelcomePageStudent.class);
                        startActivity(in);
                    } else
                        Toast.makeText(this, "Reservation not Saved", Toast.LENGTH_LONG).show();
                }
                else if (hour == 12) {
                    format = "PM";
                    boolean insert = helper.insertDataStudentReserve(idStudNum,String.valueOf(hour) + ":" + String.valueOf(min) + " " + format, etRoom.getText().toString(),
                            etSection.getText().toString());
                    if (insert) {
                        Toast.makeText(this, "Reservation Saved", Toast.LENGTH_LONG).show();
                        Intent in = new Intent(this, WelcomePageStudent.class);
                        startActivity(in);
                    } else
                        Toast.makeText(this, "Reservation not Saved", Toast.LENGTH_LONG).show();
                } else if (hour > 12) {
                    hour -= 12;
                    format = "PM";
                    boolean insert = helper.insertDataStudentReserve(idStudNum,String.valueOf(hour) + ":" + String.valueOf(min) + " " + format, etRoom.getText().toString(),
                            etSection.getText().toString());
                    if (insert) {
                        Toast.makeText(this, "Reservation Saved", Toast.LENGTH_LONG).show();
                        Intent in = new Intent(this, WelcomePageStudent.class);
                        startActivity(in);
                    } else
                        Toast.makeText(this, "Reservation not Saved", Toast.LENGTH_LONG).show();
                } else {
                    format = "AM";
                    boolean insert = helper.insertDataStudentReserve(idStudNum,String.valueOf(hour) + ":" + String.valueOf(min) + " " + format, etRoom.getText().toString(),
                            etSection.getText().toString());
                    if (insert) {
                        Toast.makeText(this, "Reservation Saved", Toast.LENGTH_LONG).show();
                        Intent in = new Intent(this, WelcomePageStudent.class);
                        startActivity(in);
                    } else
                        Toast.makeText(this, "Reservation not Saved", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
