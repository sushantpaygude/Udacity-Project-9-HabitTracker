package com.example.sushant.udacityproject9_habittrackerapp.Contracts;

import android.provider.BaseColumns;

/**
 * Created by sushant on 28/9/16.
 */
public final class HabitTrackerContract {
    //Contract class for defining table name, table columns, constants, etc.
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="database.db";
    public static final String TEXT_TYPE=" TEXT";
    public static final String COMMA=",";
    public static final String INT_TYPE=" INTEGER";
    //Inner class HabitEntry for table HabitEntry
    public static abstract class HabitEntry implements BaseColumns
    {
        public static final String TABLE_NAME="Habits";
        public static final String TABLE_ID="ID";
        public static final String COLUMN_HABIT_NAME="habit_name";
        public static final String COLUMN_HABIT_TIME="time";
        public static final String COLUMN_HABIT_DURATION="duration";
        public static final String COLUMN_HABIT_STATUS="status";

        public static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+TABLE_ID+INT_TYPE+" PRIMARY KEY,"+
                COLUMN_HABIT_NAME+TEXT_TYPE+COMMA+
                COLUMN_HABIT_TIME+TEXT_TYPE+COMMA+
                COLUMN_HABIT_DURATION+TEXT_TYPE+COMMA+
                COLUMN_HABIT_STATUS+INT_TYPE+" );";

        public static final String DELETE_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;
    }
}


