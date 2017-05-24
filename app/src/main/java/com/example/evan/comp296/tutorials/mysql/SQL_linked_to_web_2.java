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
 * Created by Evan on 5/19/17.
 */

public class SQL_linked_to_web_2 extends AppCompatActivity {

    int page_num;

    WebView wv;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_webview);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar1);


        setSupportActionBar(myToolbar);


        getSupportActionBar().setTitle("MySQL");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        wv = (WebView) findViewById(R.id.webview1);

        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Bundle bundle = getIntent().getExtras();

        page_num= bundle.getInt("page_num");


        if (page_num==0){
            wv.loadUrl("http://www.mysqltutorial.org");
        } else if (page_num==1) {
            wv.loadUrl("http://www.mysqltutorial.org/what-is-mysql/");

        } else if (page_num==2) {
            wv.loadUrl("http://www.mysqltutorial.org/install-mysql/");

        } else if (page_num==3) {
            wv.loadUrl("http://www.mysqltutorial.org/mysql-sample-database.aspx");

        } else if (page_num==4) {
            wv.loadUrl("http://www.mysqltutorial.org/how-to-load-sample-database-into-mysql-database-server.aspx");
        } else if (page_num==5) {
            wv.loadUrl("http://www.mysqltutorial.org/mysql-stored-procedure-tutorial.aspx");
        }  else if (page_num==6) {
            wv.loadUrl("http://www.mysqltutorial.org/mysql-triggers.aspx");
        } else if (page_num==7) {
            wv.loadUrl("http://www.mysqltutorial.org/mysql-views-tutorial.aspx");
        } else if (page_num==8) {
            wv.loadUrl("http://www.mysqltutorial.org/mysql-functions.aspx");
        } else if (page_num==9) {
            wv.loadUrl("http://www.mysqltutorial.org/mysql-administration.aspx");
        } else if (page_num==10) {
            wv.loadUrl("http://www.mysqltutorial.org/mysql-jdbc-tutorial/");
        } else if (page_num==11) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-php-syntax.htm");
        } else if (page_num==12) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-connection.htm");
        } else if (page_num==13) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-create-database.htm");
        } else if (page_num==14) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-drop-database.htm");
        } else if (page_num==15) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-select-database.htm");
        } else if(page_num==16) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-data-types.htm");
        } else if(page_num==17) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-create-tables.htm");
        } else if(page_num==18) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-drop-tables.htm");
        } else if(page_num==19) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-insert-query.htm");
        } else if(page_num==20) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-select-query.htm");
        } else if(page_num==21) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-where-clause.htm");
        } else if(page_num==22) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-update-query.htm");
        } else if(page_num==23) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-delete-query.htm");
        } else if(page_num==24) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-like-clause.htm");
        } else if(page_num==25) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-sorting-results.htm");
        } else if(page_num==26) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-using-joins.htm");
        } else if(page_num==27) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-null-values.htm");
        } else if(page_num==28) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-regexps.htm");
        } else if(page_num==29) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-transactions.htm");
        } else if(page_num==30) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-alter-command.htm");
        } else if(page_num==31) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-indexes.htm");
        } else if(page_num==32) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-temporary-tables.htm");
        } else if(page_num==33) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-clone-tables.htm");
        } else if(page_num==34) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-database-info.htm");
        } else if(page_num==35) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-using-sequences.htm");
        } else if(page_num==36) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-handling-duplicates.htm");
        } else if(page_num==37) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-sql-injection.htm");
        } else if(page_num==38) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-database-export.htm");
        } else if(page_num==39) {
            wv.loadUrl("https://www.tutorialspoint.com/mysql/mysql-database-import.htm");
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

                startActivity(new Intent(SQL_linked_to_web_2.this, Note_drv.class));

                return true;

            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
