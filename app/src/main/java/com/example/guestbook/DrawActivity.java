package com.example.guestbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.parse.ParseUser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class DrawActivity extends AppCompatActivity {
    private Button btnSave;
    private String eventName;
    private String eventDate;
    private String eventLocation;
    private String eventDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Bundle a = new Bundle();
        a = getIntent().getExtras();
        eventDate = a.getString("eventdate");
        eventLocation = a.getString("eventlocation");
        eventDetails = a.getString("eventdetails");
        eventName = a.getString("eventname");

        PaintView paintView = new PaintView(this);
        setContentView(paintView);
        //onBackPressed();



        //RelativeLayout parent = (RelativeLayout) findViewById(R.id.rlDraw);
        //MyDrawView myDrawView = new MyDrawView(this);
        //setContentView(myDrawView);
        //parent.addView(myDrawView);

//        parent.setDrawingCacheEnabled(true);
//        Bitmap b = parent.getDrawingCache();
//
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(getFilesDir());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        b.compress(Bitmap.CompressFormat.PNG, 95, fos);
    }

    public void onBackPressed() {
        Intent returnIntent = new Intent(this, ComposeActivity.class);
        returnIntent.putExtra("eventname", eventName);
        returnIntent.putExtra("eventdate", eventDate);
        returnIntent.putExtra("eventlocation", eventLocation);
        returnIntent.putExtra("eventdetails", eventDetails);
        setResult(1, returnIntent);
        startActivity(returnIntent);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.Logout) {
            ParseUser.logOut();;
            ParseUser currentUser = ParseUser.getCurrentUser();
            goLoginActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void goLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}