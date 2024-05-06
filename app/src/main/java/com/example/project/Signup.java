package com.example.project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {
    private FirebaseAuth mAuth;

    String signup_username;
    String signup_email;
    String signup_password;
    String signup_confirmpassword;
    TextView signup_loginlink;
    Button signup_submit, signup_continuewithgooogle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();

        signup_username = ((EditText)findViewById(R.id.signup_username)).getText().toString();
        signup_email = ((EditText)findViewById(R.id.signup_email)).getText().toString();
        signup_password = ((EditText)findViewById(R.id.signup_password)).getText().toString();
        signup_confirmpassword = ((EditText)findViewById(R.id.signup_confirmpassword)).getText().toString();
        signup_loginlink = findViewById(R.id.signup_loginlink);
        signup_loginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
        signup_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(signup_email)) {
                    Toast.makeText(Signup.this, "Email Is Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(signup_password)) {
                    Toast.makeText(Signup.this, "Email Is Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (signup_password != signup_confirmpassword) {
                    Toast.makeText(Signup.this, "PASSWORDS DO NOT MATCH, PLEASE TRY AGAIN", Toast.LENGTH_SHORT).show();
                }
                mAuth.createUserWithEmailAndPassword(signup_email, signup_password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
//                                    Log.d(TAG, "createUserWithEmail:success");
//                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(Signup.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Signup.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }
}