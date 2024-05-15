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

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class detailttrain extends AppCompatActivity {
    TextView headDetail, bodyDetail, priceDetail;
    String ticketName, ticketAge, ticketGender, ticketPhoneNumber;
    Button save;
    TicketDatabase ticketDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailttrain);
        headDetail = findViewById(R.id.priceDetail);
        bodyDetail = findViewById(R.id.headDetail);
        priceDetail = findViewById(R.id.bodyDetail);
        save = findViewById(R.id.detailSubmit);

        String head = getIntent().getStringExtra("head");
        String body = getIntent().getStringExtra("body");
        String price = getIntent().getStringExtra("price");
        String date = "null";
        headDetail.setText(price); // oh = b op = h ob =p
        bodyDetail.setText(head);
        priceDetail.setText(body);

        RoomDatabase.Callback myCallBack = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };

        ticketDatabase = Room.databaseBuilder(getApplicationContext(), TicketDatabase.class, "TicketDB").addCallback(myCallBack).build();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save = findViewById(R.id.detailSubmit);
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

                Ticket t1 = new Ticket(head, body, price, date, ticketName, ticketAge, ticketPhoneNumber, ticketGender );

                addTicketInBakground(t1);

                Toast.makeText(detailttrain.this, "Save Button clicked", Toast.LENGTH_SHORT).show();

            }
        });

    }
    public void addTicketInBakground(Ticket ticket){
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                ticketDatabase.getTicketDAO().addTicket(ticket);

                //
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(detailttrain.this, "Added to Database", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), payment.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}