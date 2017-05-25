package com.example.evan.comp296.tutorials.c_plus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.evan.comp296.Data;
import com.example.evan.comp296.Notes_main.Note_drv;
import com.example.evan.comp296.R;
import com.example.evan.comp296.tutorials.mysql.mysql_recycler_adapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Evan on 5/22/17.
 */

public class C_fragment extends Fragment {


    RecyclerView c_recycler;
    plus_recycler_adapter mAdapter1;
    LinearLayoutManager mLinearLayoutManager;


    List<Object> data;


    NativeExpressAdView adView;


    public static final int ITEMS_PER_AD = 6;

    // The Native Express ad height.
    private static final int NATIVE_EXPRESS_AD_HEIGHT = 150;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=  inflater.inflate(R.layout.fragment_test, container, false);

        c_recycler = (RecyclerView) view.findViewById(R.id.mysql_recycler);


        MobileAds.initialize(getActivity(), getString(R.string.adMob_App_ID) );

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab10);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(view.getContext(), Note_drv.class));


            }
        });



        data=fill_with_data4();
        mAdapter1 = new plus_recycler_adapter(data, view.getContext());


        mLinearLayoutManager = new LinearLayoutManager(view.getContext());
        c_recycler.setLayoutManager(mLinearLayoutManager);

        addNativeExpressAds();
        setUpAndLoadNativeExpressAds();

        c_recycler.setAdapter(mAdapter1);



        return  view;

    }



    private void addNativeExpressAds() {

        // Loop through the items array and place a new Native Express ad in every ith position in
        // the items List.
        for (int i = 0; i <= data.size(); i += ITEMS_PER_AD) {
            final NativeExpressAdView adView = new NativeExpressAdView(getActivity());
            adView.setAdUnitId(getString(R.string.c_main_ad));
            //adView.setAdSize(new AdSize(320, 150));
            //adView.loadAd(new AdRequest.Builder().build());
            data.add(i, adView);
        }

    }


    private void setUpAndLoadNativeExpressAds() {
        // Use a Runnable to ensure that the RecyclerView has been laid out before setting the
        // ad size for the Native Express ad. This allows us to set the Native Express ad's
        // width to match the full width of the RecyclerView.





        final Handler handler = new Handler();


        handler.postDelayed(new Runnable() {

            @Override
            public void run() {


                c_recycler.post(new Runnable() {
                    @Override
                    public void run() {
                        final float scale = getActivity().getResources().getDisplayMetrics().density;
                        // Set the ad size and ad unit ID for each Native Express ad in the items list.
                        for (int i = 0; i <= data.size(); i += ITEMS_PER_AD) {
                            final NativeExpressAdView adView =
                                    (NativeExpressAdView) data.get(i);
                            final CardView cardView = (CardView) getActivity().findViewById(R.id.ad_card_view);
                            final int adWidth = cardView.getWidth() - cardView.getPaddingLeft()
                                    - cardView.getPaddingRight();
                            AdSize adSize = new AdSize((int) (adWidth / scale), NATIVE_EXPRESS_AD_HEIGHT);
                            adView.setAdSize(adSize);

                            //ads_loaded=true;
                            //adView.setAdUnitId(getString(R.string.java_main_ad));
                        }

                        // Load the first Native Express ad in the items list.
                        loadNativeExpressAd(0);
                    }
                });



            }

        }, 1200);



    }





    /**
     * Loads the Native Express ads in the items list.
     */
    private void loadNativeExpressAd(final int index) {

        if (index >= data.size()) {
            return;
        }

        Object item = data.get(index);
        if (!(item instanceof NativeExpressAdView)) {
            throw new ClassCastException("Expected item at index " + index + " to be a Native"
                    + " Express ad.");
        }

        final NativeExpressAdView adView = (NativeExpressAdView) item;

        // Set an AdListener on the NativeExpressAdView to wait for the previous Native Express ad
        // to finish loading before loading the next ad in the items list.
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                // The previous Native Express ad loaded successfully, call this method again to
                // load the next ad in the items list.
                loadNativeExpressAd(index + ITEMS_PER_AD);


            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // The previous Native Express ad failed to load. Call this method again to load
                // the next ad in the items list.
                Log.e("Java Main", "The previous Native Express ad failed to load. Attempting to"
                        + " load the next Native Express ad in the items list.");
                loadNativeExpressAd(index + ITEMS_PER_AD);
            }
        });

        // Load the Native Express ad.
        adView.loadAd(new AdRequest.Builder().build());
    }



    public List fill_with_data4() {

        List<Data> data = new ArrayList();


        data.add(new Data("Intro"));
        data.add(new Data("Basic Syntax"));
        data.add(new Data("Comments"));
        data.add(new Data("Data Types"));
        data.add(new Data("Variable Types"));
        data.add(new Data("Variable Scope"));
        data.add(new Data("Constants/Literals"));
        data.add(new Data("Modifier Types"));
        data.add(new Data("Storage Classes"));
        data.add(new Data("Operators"));
        data.add(new Data("Loop Types"));
        data.add(new Data("Decision Making"));
        data.add(new Data("Functions"));
        data.add(new Data("Numbers"));
        data.add(new Data("Arrays"));
        data.add(new Data("Strings"));
        data.add(new Data("Pointers"));
        data.add(new Data("References"));
        data.add(new Data("Data & Time"));
        data.add(new Data("Basic Input/Output"));
        data.add(new Data("Data Structures"));


        return data;
    }
}

