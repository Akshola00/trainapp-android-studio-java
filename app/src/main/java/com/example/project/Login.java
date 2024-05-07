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
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    String login_email;
    String login_password;

    TextView login_signuplink;
    Button login_submit;
    ImageButton login_continuewithgooogle;
    ProgressBar login_progressBar;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Toast.makeText(Login.this, "You Are Already Logged In.",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), dashboard.class);
            startActivity(intent);
            finish();        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        login_signuplink = findViewById(R.id.login_signuplink);
        login_submit = findViewById(R.id.login_submit);
        login_continuewithgooogle = findViewById(R.id.login_continuewithgooogle);
        login_progressBar = findViewById(R.id.login_progressBar);
        login_progressBar.setVisibility(View.GONE);
//        signup_progressBar = findViewById(R.id.signup_progressBar
        login_signuplink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
                finish();
            }
        });

        login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, "Dolor Sit Amet", Toast.LENGTH_SHORT).show();
                // Find TextInputLayouts
                TextInputLayout emailInputLayout = findViewById(R.id.login_email);
                TextInputLayout passwordInputLayout = findViewById(R.id.login_password);

                // Find TextInputEditTexts inside TextInputLayouts
                TextInputEditText emailEditText = (TextInputEditText) emailInputLayout.getEditText();
                TextInputEditText passwordEditText = (TextInputEditText) passwordInputLayout.getEditText();

                // Retrieve email and password values
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                Toast.makeText(Login.this, "Email: " + email + " Password: " + password, Toast.LENGTH_SHORT).show();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login.this, "Email Is Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Password Is Required", Toast.LENGTH_SHORT).show();
                    return;
                }

                login_progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                login_progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Login.this, "Authentication Successful.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), dashboard.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

}