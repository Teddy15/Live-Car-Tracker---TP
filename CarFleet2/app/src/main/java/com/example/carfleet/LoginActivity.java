package com.example.carfleet;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class   LoginActivity extends AppCompatActivity {
    Users users;
    EditText mTextEmail;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        users = new Users(this);
        mTextEmail = (EditText) findViewById(R.id.email);
        mTextPassword = (EditText) findViewById(R.id.password);
        mButtonLogin = (Button) findViewById(R.id.login_button);
        mTextViewRegister = (TextView) findViewById(R.id.register_button);

        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mTextEmail.getText().toString();
                String password = mTextPassword.getText().toString();

                User user = users.findByEmail(email);
                if(user != null) {
                    if (validate(email, password, user)) {
                        Snackbar.make(mButtonLogin, "Successfully Logged In!", Snackbar.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Snackbar.make(mButtonLogin, "Login Error! Please Try Again!", Snackbar.LENGTH_LONG).show();

                    }
                } else {
                    Snackbar.make(mButtonLogin, "You do not have an account! Please register!", Snackbar.LENGTH_LONG).show();

                }
            }

        });
    }

    public boolean validate(String email, String password, User user) {
        boolean valid = true;

        if(!user.getEmail().equals(email) && !user.getPassword().equals(password)) {
            valid = false;
            mTextEmail.setError("Your email or your password is incorrect!");
        } else {
            mTextEmail.setError(null);
            mTextPassword.setError(null);
        }

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
                mTextPassword.setError("Password is to short!");
            }
        }

        return valid;
    }
}