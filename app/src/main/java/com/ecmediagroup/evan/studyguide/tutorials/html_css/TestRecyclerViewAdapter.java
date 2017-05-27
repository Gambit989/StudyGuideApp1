package com.ecmediagroup.evan.studyguide.tutorials.html_css;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecmediagroup.evan.studyguide.Data;
import com.ecmediagroup.evan.studyguide.Header_html;
import com.ecmediagroup.evan.studyguide.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class TestRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Object> contents = Collections.emptyList();

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    Context ctx;

    public TestRecyclerViewAdapter(List<Object> contents, Context context) {
        this.contents = contents;
        this.ctx=context;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_HEADER:
                View header= LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                return new HeaderViewHolder(header);


            case TYPE_CELL:
                View menuItemLayoutView= LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_card_small, parent, false);
                return new MenuItemViewHolder2(menuItemLayoutView);


        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int color = R.color.amber_200;

        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                HeaderViewHolder headerItemHolder = (HeaderViewHolder) holder;
                Header_html header_html = (Header_html) contents.get(position);

                headerItemHolder.header.setBackgroundColor(ctx.getResources().getColor(R.color.amber_500));
                headerItemHolder.header.setImageResource(header_html.getHeader());
                break;
            case TYPE_CELL:

                MenuItemViewHolder2 menuItemHolder = (MenuItemViewHolder2) holder;
                Data menuItem = (Data) contents.get(position);
                // Add the menu item details to the menu item view.

                menuItemHolder.category.setBackgroundColor(ctx.getResources().getColor(R.color.blue_grey_400));
                menuItemHolder.category.setText(menuItem.getJava());
                break;
        }

    }


    public class MenuItemViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView category;


        //4
        public MenuItemViewHolder2(View v) {
            super(v);


            category = (TextView) v.findViewById(R.id.html_textview);

            category.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {

            int i = v.getId();
            if (i == R.id.html_textview) {

                Intent intent = new Intent(v.getContext(), html_main_webview.class);

                Bundle bundle = new Bundle();

                bundle.putInt("page_num", getLayoutPosition());

                intent.putExtras(bundle);

                v.getContext().startActivity(intent);
            }

        }

    }


    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        private ImageView header;

        HeaderViewHolder(View view) {
            super(view);

            header = (ImageView) view.findViewById(R.id.header_html);
        }
    }


}