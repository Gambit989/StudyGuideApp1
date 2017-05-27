package com.ecmediagroup.evan.studyguide.tutorials.java;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ecmediagroup.evan.studyguide.Data;
import com.ecmediagroup.evan.studyguide.R;
import com.google.android.gms.ads.NativeExpressAdView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Evan on 5/8/17.
 */

public class Java_Recycler_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    Context ctx;

    HashMap<Integer,Class> hash = new HashMap<>();

    List<Data> list = Collections.emptyList();



    private static final int MENU_ITEM_VIEW_TYPE = 0;

    // The Native Express ad view type.
    private static final int NATIVE_EXPRESS_AD_VIEW_TYPE = 1;


    // The list of Native Express ads and menu items.
    private List<Object> mRecyclerViewItems =Collections.emptyList();;



    public Java_Recycler_Adapter(List<Object> myDataset, Context context) {

        this.mRecyclerViewItems = myDataset;
        this.ctx = context;


    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MENU_ITEM_VIEW_TYPE:
                View menuItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.recycler_layout_java, parent, false);
                return new MenuItemViewHolder(menuItemLayoutView);
            case NATIVE_EXPRESS_AD_VIEW_TYPE:
                // fall through
            default:
                View nativeExpressLayoutView = LayoutInflater.from(
                        parent.getContext()).inflate(R.layout.native_express_ad_container,
                        parent, false);
                return new NativeExpressAdViewHolder(nativeExpressLayoutView);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int viewType = getItemViewType(position);
        switch (viewType) {
            case MENU_ITEM_VIEW_TYPE:
                MenuItemViewHolder menuItemHolder = (MenuItemViewHolder) holder;
                Data menuItem = (Data) mRecyclerViewItems.get(position);
                // Add the menu item details to the menu item view.

                menuItemHolder.category.setText(menuItem.getJava());

                //menuItemHolder.category.setText(mRecyclerViewItems.get(position).toString());
                break;
            case NATIVE_EXPRESS_AD_VIEW_TYPE:
                // fall through
            default:
                NativeExpressAdViewHolder nativeExpressHolder =
                        (NativeExpressAdViewHolder) holder;
                NativeExpressAdView adView =
                        (NativeExpressAdView) mRecyclerViewItems.get(position);
                ViewGroup adCardView = (ViewGroup) nativeExpressHolder.itemView;
                // The NativeExpressAdViewHolder recycled by the RecyclerView may be a different
                // instance than the one used previously for this position. Clear the
                // NativeExpressAdViewHolder of any subviews in case it has a different
                // AdView associated with it, and make sure the AdView for this position doesn't
                // already have a parent of a different recycled NativeExpressAdViewHolder.
                if (adCardView.getChildCount() > 0) {
                    adCardView.removeAllViews();
                }
                if (adView.getParent() != null) {
                    ((ViewGroup) adView.getParent()).removeView(adView);
                }

                // Add the Native Express ad to the native express ad view.
                adCardView.addView(adView);

        }
    }


    @Override
    public int getItemCount() {
        return mRecyclerViewItems.size();
    }



    @Override
    public int getItemViewType(int position) {
        return (position % java_main.ITEMS_PER_AD == 0) ? NATIVE_EXPRESS_AD_VIEW_TYPE
                : MENU_ITEM_VIEW_TYPE;
    }


    public class MenuItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView category;


        //4
        public MenuItemViewHolder (View v) {
            super(v);


            category = (TextView) v.findViewById(R.id.java_topics_list);


            category.setOnClickListener(this);


        }


        public void addClasses(HashMap<Integer,Class> hash) {

            hash.put(0,j0.class);
            hash.put(1,j1.class);
            hash.put(2,j2.class);
            hash.put(3,j3.class);
            hash.put(4,j4.class);
            hash.put(5,j5.class);
            hash.put(6,j6.class);
            hash.put(7,j7.class);
            hash.put(8,j8.class);
            hash.put(9,j9.class);
            hash.put(10,j10.class);
            hash.put(11,j11.class);
            hash.put(12,j12.class);
            hash.put(13,j13.class);
            hash.put(14,j14.class);
            hash.put(15,j15.class);



        }


        @Override
        public void onClick(View v) {

            addClasses(hash);

            Log.d(" LOG ", hash.get(0).toString());


            //ActivityStarter a = new ActivityStarter();

            int i = v.getId();
            if (i == R.id.java_topics_list) {

                int position = getLayoutPosition();
                if (position < 6) {

                    position = position - 1;
                    v.getContext().startActivity(new Intent(v.getContext(), hash.get(position)));
                } else if (position > 6 && position < 12) {
                    position = position - 2;
                    v.getContext().startActivity(new Intent(v.getContext(), hash.get(position)));
                } else if (position > 12 && position < 18) {
                    position = position - 3;
                    v.getContext().startActivity(new Intent(v.getContext(), hash.get(position)));

                } else if (position >= 18) {
                    position = position - 4;
                    v.getContext().startActivity(new Intent(v.getContext(), hash.get(position)));
                }
            }

        }

    }


    /**
     * The {@link NativeExpressAdViewHolder} class.
     */
    public class NativeExpressAdViewHolder extends RecyclerView.ViewHolder {

        NativeExpressAdViewHolder(View view) {
            super(view);
        }
    }



}

