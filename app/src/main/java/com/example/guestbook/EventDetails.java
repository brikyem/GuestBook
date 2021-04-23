package com.example.guestbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EventDetails extends AppCompatActivity {

    private Button confirm;
    private EditText addDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details);

        confirm = findViewById(R.id.btnConfirm);
        addDetails = findViewById(R.id.etAddDetails);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myDetails = addDetails.getText().toString();
                goToTimeline(myDetails);
            }
        });
    }

    private void goToTimeline(String myDetails) {

        Intent i = new Intent(this, TimelineActivity.class);
        i.putExtra("details", myDetails);
        startActivity(i);
        finish();
    }
}