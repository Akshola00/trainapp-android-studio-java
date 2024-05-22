package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class detailttrain extends AppCompatActivity {
    TextView bodyDetail;
    String ticketName, ticketAge, ticketGender, ticketPhoneNumber;
    Button save;
    FirebaseAuth auth;
    FirebaseUser user;

    newTicketDatabase newTicketDatabase;

    ArrayList<tm> tms = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailttrain);

        bodyDetail = findViewById(R.id.bodyDetail);
        save = findViewById(R.id.detailSubmit);

        String head = getIntent().getStringExtra("head");
        String body = getIntent().getStringExtra("body");
        String price = getIntent().getStringExtra("price");
        String date = getIntent().getStringExtra("date");

        bodyDetail.setText("Train Name: " + head + "\n " + body + "\n" + "Price: " + price + " Date: " + date);
        String det = "Train detail: " + body + "\n" + "Price: " + price + " Date: " + date;
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        String cuser = user.getEmail();


        RoomDatabase.Callback myCallBack = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                super.onDestructiveMigration(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };
        newTicketDatabase = Room.databaseBuilder(getApplicationContext(), newTicketDatabase.class, "newTicketDatabase").addCallback(myCallBack).build();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                TextInputLayout detailAge = findViewById(R.id.detailAge);
                TextInputEditText detailAgeText = (TextInputEditText) detailAge.getEditText();
                ticketAge = detailAgeText.getText().toString().trim();

                TextInputLayout detailName = findViewById(R.id.detailName);
                TextInputEditText detailNameText = (TextInputEditText) detailName.getEditText();
                ticketName = detailNameText.getText().toString().trim();

                TextInputLayout detailGender = findViewById(R.id.detailGender);
                TextInputEditText detailGenderText = (TextInputEditText) detailGender.getEditText();
                ticketGender = detailGenderText.getText().toString().trim();

                TextInputLayout detailPhone = findViewById(R.id.detailPhone);
                TextInputEditText detailPhoneText = (TextInputEditText) detailPhone.getEditText();
                ticketPhoneNumber = detailPhoneText.getText().toString().trim();


                newTicket n1 = new newTicket(cuser, ticketName, ticketAge, ticketGender, ticketPhoneNumber, head, det);
                addticket(n1);
            }
        });

    }
    public void addticket(newTicket newTicket){
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                newTicketDatabase.getnewTicketDAO().addticket(newTicket);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(detailttrain.this, "Added Successfully", Toast.LENGTH_LONG).show();
                        Intent in3 = new Intent(getApplicationContext(), payment.class);
                startActivity(in3);
                    }
                });
            }
        });
    }

}