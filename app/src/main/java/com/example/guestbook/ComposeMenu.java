package com.example.guestbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ComposeMenu extends AppCompatActivity {

    private Button btnCapture;
    private Button btnUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_menu);

        btnCapture = findViewById(R.id.btnCapture);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCamera();
            }
        });

        btnUpload = findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toToAlbum();
            }
        });
    }

    private void toToAlbum() {
        Intent i = new Intent(this, ComposeActivity.class);
        startActivity(i);
        finish();
    }

    private void goToCamera() {
        Intent i = new Intent(this, ComposeActivity.class);
        startActivity(i);
        finish();
    }
}