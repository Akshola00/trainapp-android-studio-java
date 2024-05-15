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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textfield.TextInputEditText;

public class Signup extends AppCompatActivity {
    private FirebaseAuth mAuth;

    String signup_username;
    String signup_email;
    String signup_password;
    String signup_confirmpassword;
    TextView signup_loginlink;
    Button signup_submit, signup_continuewithgooogle;
    ProgressBar signup_progressBar;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Toast.makeText(Signup.this, "You Are Already Logged In.",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), dashboard.class);
            startActivity(intent);
            finish();             }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();

        signup_submit = findViewById(R.id.signup_submit);
        signup_continuewithgooogle = findViewById(R.id.signup_continuewithgooogle);
        signup_loginlink = findViewById(R.id.signup_loginlink);
        signup_progressBar = findViewById(R.id.signup_progressBar);
        signup_progressBar.setVisibility(View.GONE);
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
                // Find TextInputLayouts
//                TextInputLayout usernameInputLayout = findViewById(R.id.signup_username);
                TextInputLayout emailInputLayout = findViewById(R.id.detailAge);
                TextInputLayout passwordInputLayout = findViewById(R.id.signup_password);
                TextInputLayout confirmPasswordInputLayout = findViewById(R.id.signup_confirmpassword);

                // Find TextInputEditTexts inside TextInputLayouts
//                TextInputEditText usernameEditText = (TextInputEditText) usernameInputLayout.getEditText();
                TextInputEditText emailEditText = (TextInputEditText) emailInputLayout.getEditText();
                TextInputEditText passwordEditText = (TextInputEditText) passwordInputLayout.getEditText();
                TextInputEditText confirmPasswordEditText = (TextInputEditText) confirmPasswordInputLayout.getEditText();

                // Retrieve values
//                signup_username = usernameEditText.getText().toString().trim();
                signup_email = emailEditText.getText().toString().trim();
                signup_password = passwordEditText.getText().toString().trim();
                signup_confirmpassword = confirmPasswordEditText.getText().toString().trim();

                // Check if fields are empty
                if (TextUtils.isEmpty(signup_email)) {
                    Toast.makeText(Signup.this, "Email Is Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(signup_password)) {
                    Toast.makeText(Signup.this, "Password Is Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!signup_password.equals(signup_confirmpassword)) {
                    Toast.makeText(Signup.this, "Passwords Do Not Match", Toast.LENGTH_SHORT).show();
                    return;
                }

                signup_progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(signup_email, signup_password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                signup_progressBar.setVisibility(View.GONE);

                                if (task.isSuccessful()) {
                                    // Sign up success
                                    Toast.makeText(Signup.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), dashboard.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign up fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Signup.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
