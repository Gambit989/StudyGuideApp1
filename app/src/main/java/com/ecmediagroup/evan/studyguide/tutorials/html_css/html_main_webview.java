package com.ecmediagroup.evan.studyguide.tutorials.html_css;

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
 * Created by Evan on 5/22/17.
 */

public class html_main_webview extends AppCompatActivity {


    int page_num;

    WebView wv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_webview);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar1);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("HTML");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();

        page_num= bundle.getInt("page_num");


        wv = (WebView) findViewById(R.id.webview1);

        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);


        if (page_num==0) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_overview.htm");

        }else if (page_num==1) {
            wv.loadUrl("https://www.w3schools.com/html/default.asp");
        } else if (page_num==2) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_basic_tags.htm");

        } else if (page_num==3) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_elements.htm");

        } else if (page_num==4) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_attributes.htm");


        } else if (page_num == 5) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_formatting.htm");
        } else if (page_num == 6) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_phrase_elements.htm");
        } else if (page_num==7) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_meta_tags.htm");
        } else if (page_num==8) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_comments.htm");
        } else if (page_num==9) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_images.htm");
        } else if (page_num==10) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_tables.htm");
        } else if (page_num==11) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_lists.htm");
        } else if (page_num==12) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_text_links.htm");
        } else if (page_num==13) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_image_links.htm");
        } else if (page_num==14) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_email_links.htm");
        } else if (page_num==15) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_frames.htm");
        } else if(page_num==16) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_iframes.htm");
        } else if(page_num==17) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_blocks.htm");
        } else if(page_num==18) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_backgrounds.htm");
        } else if(page_num==19) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_colors.htm");
        } else if(page_num==20) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_fonts.htm");
        } else if(page_num==17) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_forms.htm");
        } else if(page_num==18) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_embed_multimedia.htm");
        } else if(page_num==19) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_marquees.htm");
        } else if(page_num==20) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_header.htm");
        } else if(page_num==18) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_style_sheet.htm");
        } else if(page_num==19) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_javascript.htm");
        } else if(page_num==20) {
            wv.loadUrl("https://www.tutorialspoint.com/html/html_layouts.htm");
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

                startActivity(new Intent(html_main_webview.this, Note_drv.class));

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }




}
