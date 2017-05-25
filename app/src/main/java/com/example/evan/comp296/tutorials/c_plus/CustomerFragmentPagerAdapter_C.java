package com.example.evan.comp296.tutorials.c_plus;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Evan on 5/22/17.
 */

public class CustomerFragmentPagerAdapter_C extends FragmentPagerAdapter  {

        private String[] tabTitle = new String[]{"C++ main concepts", "C#"};
        Context context;
        private int pageCount = 2;

        public CustomerFragmentPagerAdapter_C(FragmentManager fm, Context context) {
            super(fm);
            this.context=context;
        }

        @Override
        public Fragment getItem(int position)
        {
            if(position==0) {
                C_fragment Cfragment1 = new C_fragment();
                return Cfragment1;
            }else if(position==1){
                C_fragment_2 Cfragment2 = new C_fragment_2();
                return Cfragment2;
            }
            return new C_fragment();
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

