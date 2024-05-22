package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class payment extends AppCompatActivity {
    Button conf_pay_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        conf_pay_btn = findViewById(R.id.conf_pay_btn);
        TextInputEditText india = findViewById(R.id.india);
        india.setFilters(new InputFilter[] {new InputFilter.LengthFilter(3)});
        india.setInputType(InputType.TYPE_CLASS_NUMBER);
        conf_pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToTicketFragment();

            }
        });
    }

    private void navigateToTicketFragment() {
        Fragment ticketFragment = new ticketFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, ticketFragment);
        transaction.addToBackStack(null);  // Optional: Add to back stack to allow back navigation
        transaction.commit();
    }
}