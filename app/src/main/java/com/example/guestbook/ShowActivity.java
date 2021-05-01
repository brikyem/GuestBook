package com.example.guestbook;

import androidx.appcompat.app.AppCompatActivity;

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

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ShowActivity extends AppCompatActivity {

    public static final String TAG = "ShowActivity";
    private Button btnExitShow;
    private ImageView imageShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        //ImageView imageView = findViewById(R.id.ivImage);
        //AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        //animationDrawable.start();

        imageShow = findViewById(R.id.ivImage);


        queryPosts();


        btnExitShow = findViewById(R.id.btnExitShow);

        btnExitShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMenu();
            }
        });
    }

    private void backToMenu() {
        Intent intent = new Intent(this, HomepageActivity.class);
        startActivity(intent);
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
                Timer timer = new Timer();
                timer = new Timer(false);
                for (Post post : posts) {
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            ParseFile image = (ParseFile) post.getImage();
                            if (image != null){
                                image.getDataInBackground(new GetDataCallback() {
                                    @Override
                                    public void done(byte[] data, ParseException e) {
                                        if (e == null){
                                            Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                                            imageShow.setImageBitmap(bmp);
                                        }
                                    }
                                });
                            }
                            Log.i(TAG, "Post " + post.getDescription() + ", username: " + post.getUser().getUsername());
                        }
                    },0,5000);
                }
            }
        });
    }
}