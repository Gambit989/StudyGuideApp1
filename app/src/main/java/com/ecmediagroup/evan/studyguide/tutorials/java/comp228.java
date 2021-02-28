package com.ecmediagroup.evan.studyguide.tutorials.java;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ecmediagroup.evan.studyguide.Notes_main.Note_drv;
import com.ecmediagroup.evan.studyguide.R;

/**
 * Created by Evan on 5/13/17.
 */

public class comp228 extends AppCompatActivity {


    int page_num;

    WebView wv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_webview);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar1);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Comp-228");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();

        page_num= bundle.getInt("page_num");


        wv = (WebView) findViewById(R.id.webview1);

        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);


        if (page_num==0){
            wv.loadUrl("https://www.tutorialspoint.com//java/java_data_structures.htm");
        } else if (page_num==1) {
            wv.loadUrl("http://cs.lmu.edu/~ray/notes/stacks/");

        } else if (page_num==2) {
            wv.loadUrl("http://tutorials.jenkov.com/java-collections/queue.html");

        } else if (page_num==3) {
            wv.loadUrl("https://www.tutorialspoint.com/java/java_linkedlist_class.htm");

        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.webview_menu, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_favorite:

                startActivity(new Intent(comp228.this, Note_drv.class));

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }



}
