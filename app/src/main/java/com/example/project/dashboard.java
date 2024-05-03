package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.project.databinding.ActivityDashboardBinding;

public class dashboard extends AppCompatActivity {
    ActivityDashboardBinding binding;
    View search, tickets, account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new accountFragment());

        search = findViewById(R.id.search);
        tickets = findViewById(R.id.tickets);
        account = findViewById(R.id.account);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId(); // Get the item's ID
            if (itemId == R.id.search) {
                replaceFragment(new searchFragment());
            } else if (itemId == R.id.tickets) {
                replaceFragment(new ticketFragment());
            } else if (itemId == R.id.account) {
                replaceFragment(new accountFragment());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.commit();
    }
}
