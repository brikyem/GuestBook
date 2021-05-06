package com.example.guestbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.guestbook.fragment.PostsFragment;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class NewTimelineActivity extends AppCompatActivity {

    public static final String TAG = "NewTimelineActivity";
    private RecyclerView rvPosts;
    private PostsAdapter postsAdapter;
    private List<Post> allPosts;
    private Button btnBackToMenu;

    private String eventName;
    private String eventDate;
    private String eventLocation;
    private String eventDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_timeline);

        Bundle a = new Bundle();
        a = getIntent().getExtras();

        eventName = a.getString("eventname");
        eventDate = a.getString("eventdate");
        eventLocation = a.getString("eventlocation");
        eventDetails = a.getString("eventdetails");

        Fragment fragment;

        fragment = new PostsFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.flContainer, fragment).commit();

        btnBackToMenu = findViewById(R.id.btnBackToMenu1);
        btnBackToMenu.setOnClickListener(new View.OnClickListener() {
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