package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    String login_email;
    String login_password;

    TextView login_signuplink;
    Button login_login, login_continuewithgooogle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email = ((EditText)findViewById(R.id.login_email)).getText().toString();
        login_password = ((EditText)findViewById(R.id.login_password)).getText().toString();
        login_signuplink = findViewById(R.id.login_signuplink);

        login_signuplink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
                finish();
            }
        });
    }
}