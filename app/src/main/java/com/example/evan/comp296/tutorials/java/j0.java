package com.example.evan.comp296.tutorials.java;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;

import com.example.evan.comp296.Notes.Note_drv;
import com.example.evan.comp296.R;

import java.lang.reflect.Method;

/**
 * Created by Evan on 5/9/17.
 */

public class j0 extends AppCompatActivity {


    WebView wv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_webview);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab5);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(j0.this, Note_drv.class));

                /*

                Snackbar.make(view, "Email app to a friend", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                 */
            }
        });


        wv = (WebView) findViewById(R.id.webview1);

        wv.loadUrl("https://www.tutorialspoint.com/java/");



    }

}
