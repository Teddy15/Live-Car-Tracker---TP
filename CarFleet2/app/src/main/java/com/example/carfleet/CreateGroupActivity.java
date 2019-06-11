package com.example.carfleet;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Arrays;

public class CreateGroupActivity extends AppCompatActivity {
    EditText mManagerEditText;
    EditText mGroupMembersEditText;
    EditText mNameEditText;
    Button mCreateGroupButton;
    CreateGroup group;
    Users users;
    Managers managers;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_create_group);

        group = new CreateGroup(this);
        users = new Users(this);
        managers = new Managers(this);

        mManagerEditText = (EditText)findViewById(R.id.edittext_manager) ;
        mNameEditText = (EditText)findViewById(R.id.edittext_name);
        mGroupMembersEditText = (EditText)findViewById(R.id.edittext_group_members);
        mCreateGroupButton = (Button)findViewById(R.id.button_create_group);

        mCreateGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateUsers()) {
                    if (users.exists(new User(mManagerEditText.getText().toString().trim()))) {
                        Manager manager = new Manager(mManagerEditText.getText().toString().trim(),
                                Arrays.asList(mGroupMembersEditText.getText().toString().split(",")));
                        if (managers.add(manager)) {
                            Snackbar.make(mCreateGroupButton, "Successfully Created Group!", Snackbar.LENGTH_LONG).show();
                            Intent intent = new Intent(CreateGroupActivity.this, GroupActivity.class);
                            startActivity(intent);
                        } else {
                            Snackbar.make(mCreateGroupButton, "Manager is already managing another group!", Snackbar.LENGTH_LONG).show();
                        }
                    } else {
                        Snackbar.make(mCreateGroupButton, "Can not create manager with wrong username!", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public boolean validateUsers() {
        String[] groupMembers = mGroupMembersEditText.getText().toString().split(",");
        String name = mNameEditText.getText().toString();
        String manager = mManagerEditText.getText().toString().trim();
        boolean valid = true;

        for(String member : groupMembers) {
            if(!users.exists(new User(member))) {
                valid = false;
                mGroupMembersEditText.setError("One of the emails is not registered/not valid!");
                break;
            } else if(users.findByEmail(member).getIsInGroup() == 1) {
                valid = false;
                mGroupMembersEditText.setError("One of the members you are trying to add is already in another group!");
                break;
            } else if(managers.findByName(member) != null) {
                valid = false;
                mGroupMembersEditText.setError("One of your members is already a manager!");
                break;
            }
            else {
                group.insertData(name, member, manager);
                User user = users.findByEmail(member);
                users.inGroup(user, 1);
            }
        }
        return valid;
    }
}
