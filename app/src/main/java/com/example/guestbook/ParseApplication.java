package com.example.guestbook;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("KYSYOKHM1i4Z2Sw46xPNyqdDAALD0NtdPs6MKGwd")
                .clientKey("JulEJ0uK4NNERfWRMFULGg1mHSBq9KJLZeX4ovyC")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
