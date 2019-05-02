package com.example.carfleet;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class GroupActivity extends AppCompatActivity {
    CreateGroup db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        db = new CreateGroup(this);

        Cursor cursor = db.readData();
        if(cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_SHORT).show();
        }
        else {
            while(cursor.moveToNext()) {
                Toast.makeText(getApplicationContext(), "Name:" + cursor.getString(1), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Users:" + cursor.getString(2), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Manager:" + cursor.getString(3), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
