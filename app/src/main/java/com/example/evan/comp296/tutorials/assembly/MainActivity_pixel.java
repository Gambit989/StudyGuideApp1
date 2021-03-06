package com.example.evan.comp296.tutorials.assembly;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.example.evan.comp296.Data;
import com.example.evan.comp296.R;
import com.github.zagum.expandicon.ExpandIconView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_pixel extends AppCompatActivity {

	private View mBottomSheet;
	private BottomSheetBehavior mBottomSheetBehavior;
	private ExpandIconView mExpandIconView;
	private TextView pixel_text;

    private RecyclerView assembly_recycler;
	LinearLayoutManager mLinearLayoutManager;
	Assembly_recycler_adapter mAdapter1;

	private RecyclerView assembly_recycler2;
	LinearLayoutManager mLinearLayoutManager2;
	Assembly_recycler_adapter2 mAdapter2;

	private float mSlideOffset = 0;

	NativeExpressAdView adView;
	NativeExpressAdView adView2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_pixel);

		findViews();
		setUpViews();

		MobileAds.initialize(getApplicationContext(), getString(R.string.adMob_App_ID) );



		adView = (NativeExpressAdView) findViewById(R.id.assembly_adview_1);
		adView2 = (NativeExpressAdView) findViewById(R.id.assembly_native_adview_2);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		AdRequest adRequest2= new AdRequest.Builder().build();
		adView2.loadAd(adRequest2);

		mLinearLayoutManager = new LinearLayoutManager(this);
		assembly_recycler.setLayoutManager(mLinearLayoutManager);
		mAdapter1 = new Assembly_recycler_adapter(fill_with_data4(), getApplicationContext());
		assembly_recycler.setAdapter(mAdapter1);

		mLinearLayoutManager2 = new LinearLayoutManager(this);
		assembly_recycler2.setLayoutManager(mLinearLayoutManager2);
		//assembly_recycler2.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
		mAdapter2 = new Assembly_recycler_adapter2(fill_with_data(), getApplicationContext());
		assembly_recycler2.setAdapter(mAdapter2);
	}

	private void findViews() {
		mBottomSheet = findViewById(R.id.bottom_sheet);
		mExpandIconView = (ExpandIconView) mBottomSheet.findViewById(R.id.expandIconView);
        assembly_recycler = (RecyclerView) findViewById(R.id.recycler_view_pixel_1);
		assembly_recycler2 = (RecyclerView) findViewById(R.id.recycler_view_pixel_2);
		pixel_text = (TextView) findViewById(R.id.textView_pixel);


	}

	private void setUpViews() {
		mExpandIconView.setState(ExpandIconView.LESS, true);
		mBottomSheetBehavior = BottomSheetBehavior.from(mBottomSheet);
		mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

			@Override
			public void onStateChanged(@NonNull View bottomSheet, int newState) {
				if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
					mExpandIconView.setState(ExpandIconView.LESS, true);
					pixel_text.setText("Slide Up for Other Topics");
				} else if (newState == BottomSheetBehavior.STATE_EXPANDED) {
					mExpandIconView.setState(ExpandIconView.MORE, true);
					pixel_text.setText("Slide down to close");
				}
			}

			@Override
			public void onSlide(@NonNull View bottomSheet, final float slideOffset) {
				mSlideOffset = slideOffset;
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						float dis = (mSlideOffset - slideOffset) * 10;
						if (dis > 1) {
							dis = 1;
						} else if (dis < -1) {
							dis = -1;
						}
						if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_DRAGGING) {
							mExpandIconView.setFraction(.5f + dis * .5f, false);
						}
					}
				}, 150);
			}
		});

		mBottomSheetBehavior.setPeekHeight((int) convertDpToPixel(70, this));
		mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
	}

	public DisplayMetrics getDisplayMetrics(Context context) {
		Resources resources = context.getResources();
		return resources.getDisplayMetrics();
	}

	public float convertDpToPixel(float dp, Context context) {
		return dp * (getDisplayMetrics(context).densityDpi / 160f);
	}



    public List fill_with_data4() {

        List<String> data = new ArrayList();


        data.add("Chapter 1");
        data.add("Chapter 2");
        data.add("Chapter 3");
        data.add("Chapter 4");
        data.add("x86 Instruction Set");
        data.add("x86 Overview (1)");
        data.add("x86 Overview (2)");


        return data;
    }


	public List fill_with_data() {

		List<String> data = new ArrayList();


		data.add("Overview");
		data.add("OverView (2)");
		data.add("Quick Guide");
		data.add("Types");


		return data;
	}


}
