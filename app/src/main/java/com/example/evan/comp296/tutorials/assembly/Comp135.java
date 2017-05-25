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
 * Created by Evan on 5/17/17.
 */

public class Comp135 extends AppCompatActivity {


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
            wv.loadUrl("https://quizlet.com/89276850/assembly-language-for-x86-processors-by-irvine-chapter-1-flash-cards/");
        } else if (page_num == 1) {
            wv.loadUrl("https://quizlet.com/91225899/assembly-language-for-x86-processors-by-irvine-chapter-2-flash-cards/");

        } else if (page_num == 2) {
            wv.loadUrl("https://quizlet.com/94064969/assembly-language-for-x86-processors-by-irvine-chapter-3-flash-cards/");

        } else if (page_num == 3) {
            wv.loadUrl("https://quizlet.com/98187104/assembly-language-for-x86-processors-by-irvine-chapter-4-flash-cards/");

        } else if (page_num == 4) {
            wv.loadUrl("http://www.felixcloutier.com/x86/");
        } else if (page_num == 5) {
            wv.loadUrl("http://www.cs.virginia.edu/~evans/cs216/guides/x86.html");
        } else if (page_num == 6) {
            wv.loadUrl("http://cs.lmu.edu/~ray/notes/x86assembly/");
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

                startActivity(new Intent(Comp135.this, Note_drv.class));

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
