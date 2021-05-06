package com.example.guestbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class EventValidate extends AppCompatActivity {

    private EditText getEventName;
    private Button goBack;
    private Button goForward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_validate);

        getEventName = findViewById(R.id.etGetName);
        Editable name = getEventName.getText();

        goForward = findViewById(R.id.btnSearch);
        goForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEvent(name);
            }
        });



        goBack = findViewById(R.id.btnBackHome2);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMenu();
            }
        });
    }

    private void validateEvent(Editable name) {
        String dir = this.getFilesDir().getPath();
        File currentFile = new File(dir + "/" + "event/" + name + ".txt");
        if (currentFile.exists()){
            Intent intent = new Intent(this, EventHomepage.class);
            String eName = name.toString();
            intent.putExtra("eventname", eName);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(EventValidate.this, "No Such Event", Toast.LENGTH_SHORT).show();
        }

    }

    private void backToMenu() {
        Intent intent = new Intent(this, HomepageActivity.class);
        startActivity(intent);
        finish();
    }
}