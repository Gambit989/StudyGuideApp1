package com.example.evan.comp296.tutorials.c_plus;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.evan.comp296.R;
import com.example.evan.comp296.tutorials.mysql.CustomFragmentPagerAdaptor;

/**
 * Created by Evan on 5/22/17.
 */

public class c_plus_plus_main extends AppCompatActivity {




    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    AppBarLayout appBarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_main);



        toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new CustomerFragmentPagerAdapter_C(getSupportFragmentManager(), this));

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        setScrollingEnabled(true);



    }

    protected void setScrollingEnabled(boolean isEnabled) {
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        params.setScrollFlags(isEnabled ? (AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS) : 0);
    }



}


