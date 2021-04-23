// Brian-- you don't have full uploading capabilities yet since you wanted to work on navigation.
// Uploading capabilities would include everything Parstagram does + video/audio and ability to tag locations.
// I pulled a majority of this code from the Parstagram app, so use that as a starting point.
// I was thinking this app would be (in terms of UI/UX a mashup of Twitter and Instagram, kind of
// like what we were talking about. Dunno. Anyways. You should have all the resource layout files
// you need to get started. Check out the milestones on GitHub for our organization for a loose to do list.
// We're finishing Milestone 1 and 2. We can also talk more about it later in the next few nights and Tues/Wed
// Also the actionbar isn't showing the logout button on the MainActivity page :(
// Obviously you don't have to do all of this, this is just a summary of where we are right now, lol.
// 1v1 for SC2 later? :P

package com.example.guestbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import com.parse.ParseUser;

public class MainActivity_1 extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private Button btnCreate;
    private Button btnJoin;
    private Button btnPast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar;
        //TODO: fix error
        //TODO: Update appearance of ActionBar to have custom font
        actionBar = getSupportActionBar();

        btnCreate = findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEvent();
            }
        });

        btnJoin = findViewById(R.id.btnJoin);
        btnPast = findViewById(R.id.btnPast);
    }

    private void openEvent() {
        Intent i = new Intent(this, EventDetails.class);
        startActivity(i);
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