package com.example.guestbook;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EventDetailsActivity extends AppCompatActivity {

    private TextView etEventName;
    private TextView etEventDate;
    private TextView etEventLocation;
    private TextView etAddDetails;
    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        etEventName = findViewById(R.id.etEventName);
        etEventDate = findViewById(R.id.etEventDate);
        etEventLocation = findViewById(R.id.etEventLocation);
        etAddDetails = findViewById(R.id.etAddDetails);
        btnConfirm = findViewById(R.id.btnConfirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName = etEventName.getText().toString();
                String eventDate = etEventDate.getText().toString();
                String eventLocation = etEventLocation.getText().toString();
                String eventDetails = etAddDetails.getText().toString();
                goToTimeline(eventName, eventDate, eventLocation, eventDetails);
            }
        });
    }

    private void goToTimeline(String eventName, String eventDate, String eventLocation, String eventDetails) {
        Intent i = new Intent(this, TimelineActivity.class);
        i.putExtra("eventname", eventName);
        i.putExtra("eventdate", eventDate);
        i.putExtra("eventlocation", eventLocation);
        i.putExtra("eventdetails", eventDetails);
        startActivity(i);
        finish();
    }
}