package com.ecmediagroup.evan.studyguide.tutorials.c_plus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
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
 * Created by Evan on 5/24/17.
 */

public class plus_recycler_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context ctx;

    HashMap<Integer,Class> hash = new HashMap<>();

    List<Data> list = Collections.emptyList();



    private static final int MENU_ITEM_VIEW_TYPE = 0;

    // The Native Express ad view type.
    private static final int NATIVE_EXPRESS_AD_VIEW_TYPE = 1;


    // The list of Native Express ads and menu items.
    private List<Object> mRecyclerViewItems =Collections.emptyList();;


    public plus_recycler_adapter(List<Object> myDataset, Context context) {

        this.mRecyclerViewItems = myDataset;
        this.ctx = context;


    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MENU_ITEM_VIEW_TYPE:
                View menuItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.recycler_item_row_sql, parent, false);
                return new MenuItemViewHolder2(menuItemLayoutView);
            case NATIVE_EXPRESS_AD_VIEW_TYPE:
                // fall through
            default:
                View nativeExpressLayoutView = LayoutInflater.from(
                        parent.getContext()).inflate(R.layout.native_express_ad_container,
                        parent, false);
                return new NativeExpressAdViewHolder2(nativeExpressLayoutView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int viewType = getItemViewType(position);
        switch (viewType) {
            case MENU_ITEM_VIEW_TYPE:
                MenuItemViewHolder2 menuItemHolder = (MenuItemViewHolder2) holder;
                Data menuItem = (Data) mRecyclerViewItems.get(position);
                // Add the menu item details to the menu item view.

                menuItemHolder.category.setText(menuItem.getJava());

                //menuItemHolder.category.setText(mRecyclerViewItems.get(position).toString());
                break;
            case NATIVE_EXPRESS_AD_VIEW_TYPE:
                // fall through
            default:
                NativeExpressAdViewHolder2 nativeExpressHolder =
                        (NativeExpressAdViewHolder2) holder;
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
        return (position % C_fragment.ITEMS_PER_AD == 0) ? NATIVE_EXPRESS_AD_VIEW_TYPE
                : MENU_ITEM_VIEW_TYPE;
    }

    public class MenuItemViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView category;


        //4
        public MenuItemViewHolder2 (View v) {
            super(v);


            category = (TextView) v.findViewById(R.id.item_category_sql);


            category.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {



            //Log.d(" LOG ", hash.get(0).toString());


            //ActivityStarter a = new ActivityStarter();

            int i = v.getId();
            if (i == R.id.item_category_sql) {

                int position = getLayoutPosition();

                if (position < 6) {
                    position = position - 1;
                } else if (position > 6 && position < 12) {
                    position = position - 2;
                } else if (position > 12 && position < 18) {
                    position = position - 3;
                } else if (position > 18 && position < 24) {
                    position = position - 4;
                } else if (position >= 24) {
                    position = position - 5;
                }


                Intent intent = new Intent(v.getContext(), c_web.class);

                Bundle bundle = new Bundle();

                bundle.putInt("page_num", position);

                intent.putExtras(bundle);


                v.getContext().startActivity(intent);
            }
        }

    }


    /**
     * The {@link NativeExpressAdViewHolder2} class.
     */
    public class NativeExpressAdViewHolder2 extends RecyclerView.ViewHolder {

        NativeExpressAdViewHolder2(View view) {
            super(view);
        }
    }
}
