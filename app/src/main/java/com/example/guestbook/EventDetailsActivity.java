package com.example.guestbook;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;

public class EventDetailsActivity extends AppCompatActivity {

    private TextView etEventName;
    private TextView etEventDate;
    private TextView etEventLocation;
    private TextView etAddDetails;
    private Button btnConfirm;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        etEventName = findViewById(R.id.etEventName);
        etEventDate = findViewById(R.id.etEventDate);
        etEventLocation = findViewById(R.id.etEventLocation);
        etAddDetails = findViewById(R.id.etAddDetails);
        btnConfirm = findViewById(R.id.btnConfirm);

        btnBack = findViewById(R.id.btnBackHome3);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName = etEventName.getText().toString();
                if (eventName.isEmpty()){
                    Toast.makeText(EventDetailsActivity.this, "Event name cannot be blank!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String eventDate = etEventDate.getText().toString();
                String eventLocation = etEventLocation.getText().toString();
                String eventDetails = etAddDetails.getText().toString();

                writeToFile(eventName, eventDate, eventLocation, eventDetails);

                goToTimeline(eventName, eventDate, eventLocation, eventDetails);
            }
        });
    }

    private void backToHome() {
        Intent intent = new Intent(this, HomepageActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToTimeline(String eventName, String eventDate, String eventLocation, String eventDetails) {
        Intent i = new Intent(this, EventHomepage.class);
        i.putExtra("eventname", eventName);
        i.putExtra("eventdate", eventDate);
        i.putExtra("eventlocation", eventLocation);
        i.putExtra("eventdetails", eventDetails);
        setResult(1, i);
        startActivity(i);
        finish();
    }

    public void writeToFile(String fileName, String date, String location, String details){
        File dir = new File(this.getFilesDir(), "event");
        if (!dir.exists()){
            dir.mkdir();
        }
        try{
            File myFile = new File(dir, fileName + ".txt");
            FileWriter writer = new FileWriter(myFile);
            writer.append(fileName + "\n");
            writer.append(date + "\n");
            writer.append(location + "\n");
            writer.append(details);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}