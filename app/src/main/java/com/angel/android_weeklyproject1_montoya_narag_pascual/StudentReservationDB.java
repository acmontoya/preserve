package com.angel.android_weeklyproject1_montoya_narag_pascual;

import android.provider.BaseColumns;

/**
 * Created by Angel on 11/26/2016.
 */
public class StudentReservationDB {

    private StudentReservationDB(){

    }

    public static class StudentDetails implements BaseColumns {
        public static final String TABLE4 = "RESERVATIONS_TABLE";
        public static final String STUDENT_RESERVE_COL1 = "RESERVATION_ID";
        public static final String STUDENT_RESERVE_COL2 = "USER_ID";
        public static final String STUDENT_RESERVE_COL3 = "START_TIME";
        public static final String STUDENT_RESERVE_COL4 = "ROOM";
        public static final String STUDENT_RESERVE_COL5 = "SECTION";
    }
}
