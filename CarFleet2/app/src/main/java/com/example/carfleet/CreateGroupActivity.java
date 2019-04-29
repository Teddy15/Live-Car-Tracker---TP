package com.example.carfleet;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateGroupActivity extends AppCompatActivity {
    EditText mGroupMembersEditText;
    EditText mNameEditText;
    Button mCreateGroupButton;
    CreateGroup db;
    DatabaseHelper dbh;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_create_group);
        db = new CreateGroup(this);
        dbh = new DatabaseHelper(this);

        mNameEditText = (EditText)findViewById(R.id.edittext_name);
        mGroupMembersEditText = (EditText)findViewById(R.id.edittext_group_members);
        mCreateGroupButton = (Button)findViewById(R.id.button_create_group);

        mCreateGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateUsers()) {
                    Snackbar.make(mCreateGroupButton, "Successfully Created Group!", Snackbar.LENGTH_LONG).show();
                    Intent intent = new Intent(CreateGroupActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean validateUsers() {
        String[] groupMembers = mGroupMembersEditText.getText().toString().split(",");

        boolean valid = true;
        for(String member : groupMembers) {
            if(!dbh.isEmailExists(member)) {
                valid = false;
                mGroupMembersEditText.setError("One of the emails is not registered/not valid!");
                break;
            }
        }
        return valid;
    }
}
