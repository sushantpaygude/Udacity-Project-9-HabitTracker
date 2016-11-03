package com.example.sushant.udacityproject9_habittrackerapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.sushant.udacityproject9_habittrackerapp.Contracts.DatabaseHelper;
import com.example.sushant.udacityproject9_habittrackerapp.Contracts.HabitTrackerContract;

public class MainActivity extends AppCompatActivity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView);
        DatabaseHelper databaseHelper=new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db=databaseHelper.getWritableDatabase();     //Allows read and write
        insertData(db);
     Cursor cursor=readData(db,databaseHelper);
        displayData(cursor);


    }
    public void insertData(SQLiteDatabase db){
        long newRowId;

        ContentValues contentValues=new ContentValues();    //Instance of contentvalues is used to insert row values for each column
        contentValues.put(HabitTrackerContract.HabitEntry.COLUMN_HABIT_NAME,"Walk");
        contentValues.put(HabitTrackerContract.HabitEntry.COLUMN_HABIT_TIME,"Morning");
        contentValues.put(HabitTrackerContract.HabitEntry.COLUMN_HABIT_DURATION,"30 mins");
        contentValues.put(HabitTrackerContract.HabitEntry.COLUMN_HABIT_STATUS,1);
        newRowId=db.insert(HabitTrackerContract.HabitEntry.TABLE_NAME,null,contentValues);  //Each row is assigned a rowid
        contentValues.clear();

        contentValues.put(HabitTrackerContract.HabitEntry.COLUMN_HABIT_NAME,"Paint");
        contentValues.put(HabitTrackerContract.HabitEntry.COLUMN_HABIT_TIME,"Afternoon");
        contentValues.put(HabitTrackerContract.HabitEntry.COLUMN_HABIT_DURATION,"2 hours");
        contentValues.put(HabitTrackerContract.HabitEntry.COLUMN_HABIT_STATUS,0);
        newRowId=db.insert(HabitTrackerContract.HabitEntry.TABLE_NAME,null,contentValues);
        contentValues.clear();

    }

    public Cursor readData(SQLiteDatabase db,DatabaseHelper databaseHelper){
        db=databaseHelper.getReadableDatabase();


        String[] projections={HabitTrackerContract.HabitEntry.COLUMN_HABIT_NAME,    //Projections is used to specify the columns to the cursor object
                HabitTrackerContract.HabitEntry.COLUMN_HABIT_TIME,
                HabitTrackerContract.HabitEntry.COLUMN_HABIT_DURATION,
                HabitTrackerContract.HabitEntry.COLUMN_HABIT_STATUS};

        Cursor cursor=db.query(HabitTrackerContract.HabitEntry.TABLE_NAME,  //Cursor object is used to read the data in a table row-wise
                projections,
                null,
                null,
                null,
                null,
                null);
        return cursor;


    }

    public void displayData(Cursor cursor){ //Displaying the results of the cursor object returned by the readData() method
        try {
            int nameColumnIndex = cursor.getColumnIndex(HabitTrackerContract.HabitEntry.COLUMN_HABIT_NAME);
            int timeColumnIndex = cursor.getColumnIndex(HabitTrackerContract.HabitEntry.COLUMN_HABIT_TIME);
            int durationColumnIndex = cursor.getColumnIndex(HabitTrackerContract.HabitEntry.COLUMN_HABIT_DURATION);
            int statusColumnIndex = cursor.getColumnIndex(HabitTrackerContract.HabitEntry.COLUMN_HABIT_STATUS);

            while (cursor.moveToNext()) {
                String currentName = cursor.getString(nameColumnIndex);
                String currentTime = cursor.getString(timeColumnIndex);
                String currentDuration = cursor.getString(durationColumnIndex);
                int currentStatus = cursor.getInt(statusColumnIndex);
                textView.append("\n" + "--" + currentName + "--" + currentTime + "--" + currentDuration + "--" + currentStatus);
            }
        }catch (Exception e){}

        finally {
            cursor.close();
        }
    }

}
