package com.example.evan.comp296.tutorials.mysql;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Evan on 5/14/17.
 */

public class CustomFragmentPagerAdaptor extends FragmentPagerAdapter {


    private String[] tabTitle = new String[]{"SQL", "MYSQL", "SQLite", "MySQLi", "Oracle", "Memchached", "MongoDB"};
    Context context;
    private int pageCount = 7;

    public CustomFragmentPagerAdaptor(FragmentManager fm, Context context) {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position)
    {
        if(position==0) {
            SQL_fragment SQLfragment1 = new SQL_fragment();
            return SQLfragment1;
        }else if(position==1){
            SQL_fragment_2 SQLfragment2 = new SQL_fragment_2();
            return SQLfragment2;
        }
        return new SQL_fragment();
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
