package com.abhishek.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            //Create Database if not exists
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);

            //Create Table in Database if not exists
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");
            
            //Insert Data in the table
            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Abhishek', 20)");
            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Aditya', 18)");

            //For taking data from the database
            //Cursor allows us to loop through all of the results of a particular query
            Cursor c = myDatabase.rawQuery("SELECT * FROM users", null);
            
            //For getting particular column values from table
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");

            //Take out cursor and move to the first result
            c.moveToFirst();

            //Looping through result till c is not null
            while (c != null){

                //Show the results
                Log.i("Name", c.getString(nameIndex));
                Log.i("Age", Integer.toString(c.getInt(ageIndex)));

                //To move to the next result
                c.moveToNext();
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}