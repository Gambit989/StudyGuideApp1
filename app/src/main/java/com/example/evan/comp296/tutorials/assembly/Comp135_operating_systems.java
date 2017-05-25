package com.example.evan.comp296.tutorials.assembly;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.evan.comp296.Notes_main.Note_drv;
import com.example.evan.comp296.R;

/**
 * Created by Evan on 5/24/17.
 */

public class Comp135_operating_systems extends AppCompatActivity {


    int page_num;

    WebView wv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_webview);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar1);


        setSupportActionBar(myToolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Computer Architecture");


        wv = (WebView) findViewById(R.id.webview1);

        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Bundle bundle = getIntent().getExtras();

        page_num = bundle.getInt("page_num");


        if (page_num == 0) {
            wv.loadUrl("http://homepage.cs.uri.edu/faculty/wolfe/book/Readings/Reading07.htm");
        } else if (page_num == 1) {
            wv.loadUrl("https://www.tutorialspoint.com/operating_system/os_services.htm");

        } else if (page_num == 2) {
            wv.loadUrl("https://www.tutorialspoint.com/operating_system/os_quick_guide.htm");

        } else if (page_num == 3) {
            wv.loadUrl("https://www.tutorialspoint.com/operating_system/os_types.htm");
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.webview_menu_2, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_favorite:

                startActivity(new Intent(Comp135_operating_systems.this, Note_drv.class));

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}



