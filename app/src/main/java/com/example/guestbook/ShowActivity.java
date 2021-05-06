package com.example.guestbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.guestbook.fragment.PostsFragment;
import com.example.guestbook.fragment.ShowFragment;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ShowActivity extends AppCompatActivity {

    public static final String TAG = "ShowActivity";
    private Button btnExitShow;
    private ImageView imageShow;
    private TextView tvWords;
    public ArrayList<Bitmap> bmpArray = new ArrayList<Bitmap>();
    private PostsAdapter postsAdapter;

    private String eventName;
    private String eventDate;
    private String eventLocation;
    private String eventDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Bundle a = new Bundle();
        a = getIntent().getExtras();

        eventName = a.getString("eventname");
        eventDate = a.getString("eventdate");
        eventLocation = a.getString("eventlocation");
        eventDetails = a.getString("eventdetails");


        Fragment fragment;

        fragment = new ShowFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.flContainerShow, fragment).commit();


        btnExitShow = findViewById(R.id.btnExitShow);

        btnExitShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMenu(eventName, eventDate, eventLocation, eventDetails);
            }
        });
    }


    private void backToMenu(String eventName, String eventDate, String eventLocation, String eventDetails) {
        Intent i = new Intent(this, EventHomepage.class);
        i.putExtra("eventname", eventName);
        i.putExtra("eventdate", eventDate);
        i.putExtra("eventlocation", eventLocation);
        i.putExtra("eventdetails", eventDetails);
        setResult(1, i);
        startActivity(i);
        finish();
    }


    private void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (Post post : posts) {
                    Log.i(TAG, "Post " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }
            }
        });
    }
}