package com.example.guestbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TimelineActivity_dont_use extends AppCompatActivity {

    private TextView tv_eventName;
    private TextView tv_eventDate;
    private TextView tv_eventLocation;
    private TextView tv_eventDetails;
    private Button btnSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline_dontuse);

        tv_eventName = findViewById(R.id.tv_eventName);
        tv_eventDate = findViewById(R.id.tv_eventDate);
        tv_eventLocation = findViewById(R.id.tv_eventLocation);
        tv_eventDetails = findViewById(R.id.tv_eventDetails);
        btnSign = findViewById(R.id.btnSign);

        String eventname = getIntent().getStringExtra("eventname");
        String eventdate = getIntent().getStringExtra("eventdate");
        String eventlocation = getIntent().getStringExtra("eventlocation");
        String eventdetails = getIntent().getStringExtra("eventdetails");

        tv_eventName.setText(eventname);
        tv_eventDate.setText(eventdate);
        tv_eventLocation.setText(eventlocation);
        tv_eventDetails.setText(eventdetails);

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCompose();
            }
        });
    }

    private void goToCompose() {
        Intent i = new Intent(this, ComposeActivity.class);
        startActivity(i);
        finish();
    }
}