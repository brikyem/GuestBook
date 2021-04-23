package com.example.guestbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TimelineActivity extends AppCompatActivity {

    TextView eventName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline);

        eventName = findViewById(R.id.tv_eventName);

        String details = getIntent().getStringExtra("details");

        eventName.setText(details);
    }
}