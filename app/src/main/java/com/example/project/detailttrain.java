package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class detailttrain extends AppCompatActivity {
    TextView headDetail, bodyDetail, priceDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailttrain);
        headDetail = findViewById(R.id.headDetail);
        bodyDetail = findViewById(R.id.bodyDetail);
        priceDetail = findViewById(R.id.priceDetail);

        String head = getIntent().getStringExtra("head");
        String body = getIntent().getStringExtra("body");
        String price = getIntent().getStringExtra("price");

        headDetail.setText(head);
        bodyDetail.setText(body);
        priceDetail.setText(price);

    }
}