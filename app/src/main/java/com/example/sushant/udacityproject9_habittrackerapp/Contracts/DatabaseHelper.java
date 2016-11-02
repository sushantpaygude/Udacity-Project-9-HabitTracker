package com.example.sushant.udacityproject9_habittrackerapp.Contracts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sushant on 28/9/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    //Constructor connects to the database if it exists or creates a new database.
    public DatabaseHelper(Context context) {
        super(context,HabitTrackerContract.DATABASE_NAME,null,HabitTrackerContract.DATABASE_VERSION);
    }
    //onCreate method which creates new table if it doesnt exist, or opens an existing table.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HabitTrackerContract.HabitEntry.CREATE_TABLE);
        Log.e("STATEMENT","IS:"+HabitTrackerContract.HabitEntry.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL(HabitTrackerContract.HabitEntry.DELETE_TABLE);
        onCreate(db);
    }
}
