package com.example.carfleet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    Users users;
    EditText mTextEmail;
    EditText mTextPassword;
    EditText mTextConfirmPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        users = new Users(this);
        mTextEmail = (EditText) findViewById(R.id.email);
        mTextPassword = (EditText) findViewById(R.id.password);
        mTextConfirmPassword = (EditText) findViewById(R.id.confirm_password);
        mButtonRegister = (Button) findViewById(R.id.register_button);
        mTextViewLogin = (TextView) findViewById(R.id.login_button);

        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(LoginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mTextEmail.getText().toString();
                String password = mTextPassword.getText().toString();
                String confirmPassword = mTextConfirmPassword.getText().toString();

                User user = users.findByEmail(email);
                if(user == null) {
                    user = new User(email, password);
                    if (validate(email, password, confirmPassword, user)) {
                        if(users.add(user)) {
                            Snackbar.make(mButtonRegister, "User created successfully! Please Login!", Snackbar.LENGTH_LONG).show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, Snackbar.LENGTH_LONG);
                        }else {
                            Snackbar.make(mButtonRegister, "Something went wrong!", Snackbar.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Snackbar.make(mButtonRegister, "User already exists!", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
    public boolean validate(String email, String password, String confirmPassword, User user) {
        boolean valid = true;

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            valid = false;
            mTextEmail.setError("Please enter valid email!");
        } else {
            mTextEmail.setError(null);
        }

        if (password.isEmpty()) {
            valid = false;
            mTextPassword.setError("Please enter valid password!");
        } else {
            if (password.length() > 5) {
                mTextPassword.setError(null);
            } else {
                valid = false;
                mTextPassword.setError("Password is to short! Enter at least 5 symbols!");
            }
        }

        if(!password.equals(confirmPassword)) {
            valid = false;
            mTextPassword.setError("Passwords does not match!");
        }

        return valid;
    }
}