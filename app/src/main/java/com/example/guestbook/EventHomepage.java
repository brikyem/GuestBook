package com.example.guestbook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EventHomepage extends AppCompatActivity {

    private TextView tvEventName;
    private TextView tvEventDate;
    private TextView tvEventLocation;
    private TextView tvEventDesc;
    private Button btnViewTimeline;
    private Button btnViewSlideshow;
    private Button btnAddEntry;
    private Button btnLeaveEvent;
    private Button btnDraw;

    private String eventName;
    private String eventDate;
    private String eventLocation;
    private String eventDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_homepage);

        tvEventName = findViewById(R.id.tvEventName);
        tvEventDate = findViewById(R.id.tvDate);
        tvEventLocation = findViewById(R.id.tvLocation);
        tvEventDesc = findViewById(R.id.tvDesc);

        Bundle a = new Bundle();
        a = getIntent().getExtras();

        eventName = a.getString("eventname");

        String dir = this.getFilesDir().getPath();
        String fileName = eventName;

        File currentFile = new File(dir + "/" + "event/" + fileName + ".txt");
        try {
            Scanner readFile = new Scanner(currentFile);
            int count = 0;
            while(count < 4){
                    String eventInfo = readFile.nextLine();
                    if (count == 0){
                        tvEventName.setText(eventInfo);
                    }
                    else if (count == 1){
                        tvEventDate.setText(eventInfo);
                    }
                    else if (count == 2){
                        tvEventLocation.setText(eventInfo);
                    }
                    else{
                        tvEventDesc.setText(eventInfo);
                    }
                    count++;
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

/*        btnDraw = findViewById(R.id.btnDraw);
        btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDraw();
            }
        });*/

        btnLeaveEvent = findViewById(R.id.btnBackHome);
        btnLeaveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHome();
            }
        });

//        eventDate = a.getString("eventdate");
//        eventLocation = a.getString("eventlocation");
//        eventDetails = a.getString("eventdetails");
//
//        tvEventName.setText(eventName);
//        tvEventDate.setText(eventDate);
//        tvEventLocation.setText(eventLocation);
//        tvEventDesc.setText(eventDetails);

        btnViewTimeline = findViewById(R.id.btnViewTimeline);
        btnViewTimeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTimeline(eventName, eventDate, eventLocation, eventDetails);
            }
        });

        btnViewSlideshow = findViewById(R.id.btnViewSlideshow);
        btnViewSlideshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSlideshow(eventName, eventDate, eventLocation, eventDetails);
            }
        });

        btnAddEntry = findViewById(R.id.btnCompose);
        btnAddEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCompose(eventName, eventDate, eventLocation, eventDetails);
            }
        });
    }

//    private void goToDraw() {
//        Intent intent = new Intent(this, DrawActivity.class);
//        startActivity(intent);
//        finish();
//    }

    private void goToHome() {
        Intent intent = new Intent(this, HomepageActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            String eventName = getIntent().getStringExtra("eventname");
            String eventDate = getIntent().getStringExtra("eventdate");
            String eventLocation = getIntent().getStringExtra("eventlocation");
            String eventDetails = getIntent().getStringExtra("eventdetails");

            tvEventName.setText(eventName);
            tvEventDate.setText(eventDate);
            tvEventLocation.setText(eventLocation);
            tvEventDesc.setText(eventDetails);
        }
    }

    private void readFile(){

    }

    private void goToCompose(String eventName, String eventDate, String eventLocation, String eventDetails) {
            Intent i = new Intent(this, ComposeActivity.class);
            i.putExtra("eventname", eventName);
            i.putExtra("eventdate", eventDate);
            i.putExtra("eventlocation", eventLocation);
            i.putExtra("eventdetails", eventDetails);
            setResult(1, i);
            startActivity(i);
            finish();
    }

    private void goToSlideshow(String eventName, String eventDate, String eventLocation, String eventDetails) {
        Intent i = new Intent(this, ShowActivity.class);
        i.putExtra("eventname", eventName);
        i.putExtra("eventdate", eventDate);
        i.putExtra("eventlocation", eventLocation);
        i.putExtra("eventdetails", eventDetails);
        setResult(1, i);
        startActivity(i);
        finish();
    }

    private void goToTimeline(String eventName, String eventDate, String eventLocation, String eventDetails) {
        Intent i = new Intent(this, NewTimelineActivity.class);
        i.putExtra("eventname", eventName);
        i.putExtra("eventdate", eventDate);
        i.putExtra("eventlocation", eventLocation);
        i.putExtra("eventdetails", eventDetails);
        setResult(1, i);
        startActivity(i);
        finish();
    }
}