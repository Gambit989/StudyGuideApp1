package com.example.evan.comp296.tutorials.mysql;

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

public class SQL_linked_to_web extends AppCompatActivity {


    int page_num;

    WebView wv;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_webview);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar1);


        setSupportActionBar(myToolbar);


        getSupportActionBar().setTitle("SQL");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        wv = (WebView) findViewById(R.id.webview1);

        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Bundle bundle = getIntent().getExtras();

        page_num= bundle.getInt("page_num");


        if (page_num==0){
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-overview.htm");
        } else if (page_num==1) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-rdbms-concepts.htm");

        } else if (page_num==2) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-databases.htm");

        } else if (page_num==3) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-syntax.htm");

        } else if (page_num==4) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-data-types.htm");
        } else if (page_num==5) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-operators.htm");
        }  else if (page_num==6) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-expressions.htm");
         } else if (page_num==7) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-create-database.htm");
        } else if (page_num==8) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-drop-database.htm");
        } else if (page_num==9) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-select-database.htm");
        } else if (page_num==10) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-create-table.htm");
        } else if (page_num==11) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-drop-table.htm");
        } else if (page_num==12) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-insert-query.htm");
        } else if (page_num==13) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-select-query.htm");
        } else if (page_num==14) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-where-clause.htm");
        } else if (page_num==15) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-and-or-clauses.htm");
        } else if(page_num==16) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-update-query.htm");
        } else if(page_num==17) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-delete-query.htm");
        } else if(page_num==18) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-like-clause.htm");
        } else if(page_num==19) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-top-clause.htm");
        } else if(page_num==20) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-order-by.htm");
        } else if(page_num==21) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-group-by.htm");
        } else if(page_num==22) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-distinct-keyword.htm");
        } else if(page_num==23) {
            wv.loadUrl("https://www.tutorialspoint.com/sql/sql-sorting-results.htm");
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

                startActivity(new Intent(SQL_linked_to_web.this, Note_drv.class));

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
