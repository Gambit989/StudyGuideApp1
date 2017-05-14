package com.example.evan.comp296.tutorials.mysql;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Evan on 5/14/17.
 */

public class CustomFragmentPagerAdaptor extends FragmentPagerAdapter {


    private String[] tabTitle = new String[]{"SQL", "MYSQL", "SQLite", "Oracle", "JAVASCRIPT", "PYTHON", "RUBY", "C"};
    Context context;
    private int pageCount = 7;

    public CustomFragmentPagerAdaptor(FragmentManager fm, Context context) {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position)
    {
        FragmentTest fragmentTest = new FragmentTest();
        return fragmentTest;
    }

    @Override
    public int getCount()
    {
        return pageCount;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return tabTitle[position];
    }



}
