package com.ecmediagroup.evan.studyguide.tutorials.c_plus;

import android.os.Bundle;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ecmediagroup.evan.studyguide.R;

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


