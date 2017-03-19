package com.angel.android_weeklyproject1_montoya_narag_pascual;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;
    private static final String DATABASE = "PReserveDB";
    private static final String TABLE1 = "ADMIN_TABLE";
    private static final String TABLE2 = "STUDENT_TABLE";
    private static final String TABLE3 = "USERS_TABLE";
    private static final String TABLE4 = "RESERVATIONS_TABLE";
    //ADMIN TABLE1
    private static final String ADMIN_COL1 = "ADMIN_ID";
    private static final String ADMIN_COL2 = "ADMIN_UST_ID";
    private static final String ADMIN_COL3 = "LASTNAME";
    private static final String ADMIN_COL4 = "FIRSTNAME";
    private static final String ADMIN_COL5 = "PASSWORD";
    private static final String ADMIN_COL6 = "USER_TYPE";
    //STUDENT TABLE2
    private static final String STUDENT_COL1 = "STUDENT_ID";
    private static final String STUDENT_COL2 = "STUDENT_USER_ID";
    private static final String STUDENT_COL3 = "LASTNAME";
    private static final String STUDENT_COL4 = "FIRSTNAME";
    private static final String STUDENT_COL5 = "COURSE";
    private static final String STUDENT_COL6 = "PASSWORD";
    private static final String STUDENT_COL7 = "USER_TYPE";
    //USERS TABLE3
    private static final String USERS_COL1 = "USER_ID";
    private static final String USERS_COL2 = "USER_TYPE";
    private static final String USERS_COL3 = "ID_NUM";
    //Reservations table4
    private static final String STUDENT_RESERVE_COL1 = "RESERVATION_ID";
    private static final String STUDENT_RESERVE_COL2 = "USER_ID";
    private static final String STUDENT_RESERVE_COL3 = "START_TIME";
    private static final String STUDENT_RESERVE_COL4 = "ROOM";
    private static final String STUDENT_RESERVE_COL5 = "SECTION";



    private static final String CREATE_TABLE1 = "CREATE TABLE IF NOT EXISTS ADMIN_TABLE (ADMIN_ID INTEGER PRIMARY KEY AUTOINCREMENT, ADMIN_UST_ID INTEGER UNIQUE, LASTNAME VARCHAR(20), FIRSTNAME VARCHAR(20), PASSWORD VARCHAR(10), USER_TYPE INTEGER DEFAULT 1)";
    //private static final String CREATE_TABLE3 = "CREATE TABLE USERS_TABLE (USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,  USER_TYPE TEXT, ID_NUM INTEGER, FOREIGN KEY (ID_NUM) REFERENCES ADMIN_TABLE(ADMIN_ID))";
    private static final String CREATE_TABLE2 = "CREATE TABLE IF NOT EXISTS STUDENT_TABLE (STUDENT_ID INTEGER PRIMARY KEY AUTOINCREMENT, STUDENT_USER_ID INTEGER UNIQUE, LASTNAME VARCHAR(20), FIRSTNAME VARCHAR(20), COURSE VARCHAR(20), PASSWORD VARCHAR(10), USER_TYPE INTEGER DEFAULT 0)";
    //private static final String CREATE_TABLE4 = "CREATE TABLE IF NOT EXISTS RESERVATIONS_TABLE (RESERVATION_ID INTEGER PRIMARY KEY AUTOINCREMENT, USER_ID INTEGER, START_TIME DATETIME DEFAULT CURRENT_TIMESTAMP, ROOM VARCHAR(10), SECTION VARCHAR(10), FOREIGN KEY(USER_ID) REFERENCES STUDENT_TABLE(STUDENT_USER_ID))";

    private static final String CREATE_TABLE4 = "CREATE TABLE IF NOT EXISTS " +
                                                StudentReservationDB.StudentDetails.TABLE4 + "(" +
                                                StudentReservationDB.StudentDetails._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                StudentReservationDB.StudentDetails.STUDENT_RESERVE_COL2 + " INTEGER, " +
                                                StudentReservationDB.StudentDetails.STUDENT_RESERVE_COL3 + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                                                StudentReservationDB.StudentDetails.STUDENT_RESERVE_COL4 + " VARCHAR(10), " +
                                                StudentReservationDB.StudentDetails.STUDENT_RESERVE_COL5 + " VARCHAR(10), FOREIGN KEY(USER_ID) REFERENCES STUDENT_TABLE(STUDENT_USER_ID))";


    private static final String DROP_TABLE1 = "DROP TABLE IF EXIST " + TABLE1;
    private static final String QUERY1 = "SELECT * FROM " + TABLE1;
    private static final String DROP_TABLE2 = "DROP TABLE IF EXIST " + TABLE2;
    private static final String QUERY2 = "SELECT * FROM " + TABLE2;
    private static final String DROP_TABLE3 = "DROP TABLE IF EXIST " + TABLE3;
    private static final String QUERY3 = "SELECT * FROM " + TABLE3;
    private static final String DROP_TABLE4 = "DROP TABLE IF EXIST " + TABLE4;
    private static final String QUERY4 = "SELECT * FROM " + TABLE4;

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, 1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE1);
        db.execSQL(CREATE_TABLE2);
        db.execSQL(CREATE_TABLE4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE1);
        db.execSQL(DROP_TABLE2);
        db.execSQL(DROP_TABLE4);
        onCreate(db);
    }

    public boolean insertDataAdminReg(String idAdminnum, String lastname, String firstname, String pass) {

        ContentValues cv = new ContentValues();
        cv.put(ADMIN_COL2,idAdminnum);
        cv.put(ADMIN_COL3,lastname);
        cv.put(ADMIN_COL4,firstname);
        cv.put(ADMIN_COL5,pass);
        long result = db.insert(TABLE1, null, cv);
        if (result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public boolean insertDataStudentReg(String idStudNum, String lastname, String firstname, String course, String pass) {
        ContentValues cv = new ContentValues();
        cv.put(STUDENT_COL2,idStudNum);
        cv.put(STUDENT_COL3,lastname);
        cv.put(STUDENT_COL4,firstname);
        cv.put(STUDENT_COL5,course);
        cv.put(STUDENT_COL6,pass);
        long result = db.insert(TABLE2, null, cv);
        if (result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public boolean insertDataStudentReserve(String studNum, String startTime, String room, String section) {
        ContentValues cv = new ContentValues();
        cv.put(STUDENT_RESERVE_COL2,studNum);
        cv.put(STUDENT_RESERVE_COL3,startTime);
        cv.put(STUDENT_RESERVE_COL4,room);
        cv.put(STUDENT_RESERVE_COL5,section);
        long result = db.insert(TABLE4, null, cv);
        if (result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public String getUserAccount(String idNumStudNum) {
        Cursor cursor1 = db.query("ADMIN_TABLE", null, " ADMIN_UST_ID=?", new String[]{idNumStudNum}, null, null, null);

        if(cursor1.getCount()<1) {
            cursor1.close();
            return "Account does not exist.";
        }
        cursor1.moveToFirst();
        String password= cursor1.getString(cursor1.getColumnIndex("PASSWORD"));
        cursor1.close();
        return password;
    }

    public String getUserAccountStudent(String studNum) {
        Cursor cursor2 = db.query("STUDENT_TABLE", null, " STUDENT_USER_ID=?", new String[]{studNum}, null, null, null);

        if(cursor2.getCount()<1) {
            cursor2.close();
            return "Account does not exist.";
        }
        cursor2.moveToFirst();
        String password= cursor2.getString(cursor2.getColumnIndex("PASSWORD"));
        cursor2.close();
        return password;
    }


    public String getUserLastNameStudent(String studNum){
        Cursor cursor2 = db.query("STUDENT_TABLE", null, " STUDENT_USER_ID=?", new String[]{studNum}, null, null, null);

        cursor2.moveToFirst();
        String lname= cursor2.getString(cursor2.getColumnIndex("LASTNAME"));
        cursor2.close();
        return lname;
    }


    public String getUserFirstNameStudent(String studNum){
        Cursor cursor2 = db.query("STUDENT_TABLE", null, " STUDENT_USER_ID=?", new String[]{studNum}, null, null, null);

        cursor2.moveToFirst();
        String fname= cursor2.getString(cursor2.getColumnIndex("FIRSTNAME"));
        cursor2.close();
        return fname;
    }

    public String getStudentNumber(String studNum){
        Cursor cursor2 = db.query("STUDENT_TABLE", null, " STUDENT_USER_ID=?", new String[]{studNum}, null, null, null);

        cursor2.moveToFirst();
        String sNum= cursor2.getString(cursor2.getColumnIndex("STUDENT_USER_ID"));
        cursor2.close();
        return sNum;
    }

    public boolean isRecordExistingAdmin(String idNum) {
        boolean exist = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE1;
        if (idNum != null) {
            sql = sql + " where ADMIN_UST_ID = '" + idNum + "'";
        }
        Cursor c = db.rawQuery(sql, null);
        if (c != null) {
            if (c.getCount() > 0)
                exist = true;
        }
        return exist;
    }

    public boolean isRecordExistingStudent(String studNum) {
        boolean exist = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE2;
        if (studNum != null) {
            sql = sql + " where STUDENT_USER_ID = '" + studNum + "'";
        }
        Cursor c = db.rawQuery(sql, null);
        if (c != null) {
            if (c.getCount() > 0)
                exist = true;
        }
        return exist;
    }

    public Cursor getData(){
        return db.rawQuery(QUERY1, null);
    }
}