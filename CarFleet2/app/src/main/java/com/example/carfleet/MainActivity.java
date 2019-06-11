package com.example.carfleet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    ImageButton CarButton;
    ImageButton CreateGroupButton;
    ImageButton LogOutButton;
    Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users = new Users(this);

        CarButton = (ImageButton) findViewById(R.id.car_button);
        CreateGroupButton = (ImageButton)findViewById(R.id.create_group_button);
        LogOutButton = (ImageButton) findViewById(R.id.log_out_button);

        CarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadCarActivity = new Intent(MainActivity.this, CarActivity.class);
                startActivity(intentLoadCarActivity);
            }
        });

        CreateGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadCreateGroupActivity = new Intent(MainActivity.this, CreateGroupActivity.class);
                startActivity(intentLoadCreateGroupActivity);
            }
        });

        LogOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogOut = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentLogOut);
            }
        });
    }
}
