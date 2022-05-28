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
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS newUsers (name VARCHAR, age INTEGER, id INTEGER PRIMARY KEY AUTOINCREMENT)");
            
            //Insert Data in the table
            myDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Abhishek', 20)");
            myDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Aditya', 18)");
            myDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Akash', 22)");
            myDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Peeyush', 19)");
            myDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Peeyush', 19)");
            myDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Shubham', 21)");
            myDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Karan', 25)");
            myDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Shivam', 23)");

            //For deleting duplicate data or a data from table
            myDatabase.execSQL("DELETE FROM newUsers WHERE id=4");

            //For updating a value from the result suppose change the age to 24 whose name contains 'K' in his name
            myDatabase.execSQL("UPDATE newUsers SET age = 24 WHERE name LIKE '%k%'");

            //For taking data from the database
            //Cursor allows us to loop through all of the results of a particular query
            Cursor c = myDatabase.rawQuery("SELECT * FROM newUsers", null);


            
            //For getting particular column values from table
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");

            //Take out cursor and move to the first result
            c.moveToFirst();

            //Looping through result till c is not null
            while (c != null){

                //Show the results
                Log.i("Name", c.getString(nameIndex));
                Log.i("Age", Integer.toString(c.getInt(ageIndex)));
                Log.i("Id", Integer.toString(c.getInt(idIndex)));

                //To move to the next result
                c.moveToNext();
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}