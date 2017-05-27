package com.ecmediagroup.evan.studyguide.tutorials.c_plus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ecmediagroup.evan.studyguide.Notes_main.Note_drv;
import com.ecmediagroup.evan.studyguide.R;

/**
 * Created by Evan on 5/24/17.
 */

public class c_web extends AppCompatActivity {


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
            wv.loadUrl("https://www.tutorialspoint.com/cplusplus/cpp_overview.htm");
        } else if (page_num == 1) {
            wv.loadUrl("https://www.tutorialspoint.com/cplusplus/cpp_basic_syntax.htm");

        } else if (page_num == 2) {
            wv.loadUrl("https://www.tutorialspoint.com/cplusplus/cpp_comments.htm");

        } else if (page_num == 3) {
            wv.loadUrl("https://www.tutorialspoint.com/cplusplus/cpp_data_types.htm");

        } else if (page_num == 4) {
            wv.loadUrl("https://www.tutorialspoint.com/cplusplus/cpp_variable_types.htm");
        } else if (page_num == 5) {
            wv.loadUrl("https://www.tutorialspoint.com/cplusplus/cpp_variable_scope.htm");
        } else if (page_num == 6) {
            wv.loadUrl("https://www.tutorialspoint.com/cplusplus/cpp_constants_literals.htm");
        } else if (page_num==7) {
            wv.loadUrl("https://www.tutorialspoint.com/cplusplus/cpp_modifier_types.htm");
        } else if (page_num==8) {
            wv.loadUrl("https://www.tutorialspoint.com/cplusplus/cpp_storage_classes.htm");
        } else if (page_num==9) {
            wv.loadUrl("https://www.tutorialspoint.com/cplusplus/cpp_operators.htm");
        } else if (page_num==10) {
            wv.loadUrl("https://www.tutorialspoint.com/cplusplus/cpp_loop_types.htm");
        } else if (page_num==11) {
            wv.loadUrl("https://www.tutorialspoint.com/cplusplus/cpp_decision_making.htm");
        } else if (page_num==12) {
            wv.loadUrl("https://www.tutorialspoint.com/cplusplus/cpp_functions.htm");
        } else if (page_num==13) {
            wv.loadUrl("https://www.tutorialspoint.com/cplusplus/cpp_numbers.htm");
        } else if (page_num==14) {
            wv.loadUrl("https://www.tutorialspoint.com/cplusplus/cpp_arrays.htm");
        } else if (page_num==15) {
            wv.loadUrl("https://www.tutorialspoint.com/cplusplus/cpp_strings.htm");
        } else if(page_num==16) {
            wv.loadUrl("https://www.tutorialspoint.com/cplusplus/cpp_pointers.htm");
        } else if(page_num==17) {
            wv.loadUrl("https://www.tutorialspoint.com/cplusplus/cpp_references.htm");
        } else if(page_num==18) {
            wv.loadUrl("https://www.tutorialspoint.com/cplusplus/cpp_date_time.htm");
        } else if(page_num==19) {
            wv.loadUrl("https://www.tutorialspoint.com/cplusplus/cpp_basic_input_output.htm");
        } else if(page_num==20) {
            wv.loadUrl("https://www.tutorialspoint.com/cplusplus/cpp_data_structures.htm");
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

                startActivity(new Intent(c_web.this, Note_drv.class));

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }




}






