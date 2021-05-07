package com.example.guestbook;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class DrawActivity extends AppCompatActivity {
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //PaintView paintView = new PaintView(this);
        //setContentView(paintView);

        RelativeLayout parent = (RelativeLayout) findViewById(R.id.rlDraw);
        MyDrawView myDrawView = new MyDrawView(this);
        setContentView(myDrawView);
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
}