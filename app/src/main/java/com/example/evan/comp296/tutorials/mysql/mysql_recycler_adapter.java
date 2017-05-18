package com.example.evan.comp296.tutorials.mysql;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evan.comp296.Data;
import com.example.evan.comp296.MainHomeScreen;
import com.example.evan.comp296.R;
import com.example.evan.comp296.tutorials.java.Java_Recycler_Adapter;
import com.example.evan.comp296.tutorials.java.comp271;
import com.example.evan.comp296.tutorials.java.j0;
import com.example.evan.comp296.tutorials.java.j1;
import com.example.evan.comp296.tutorials.java.j10;
import com.example.evan.comp296.tutorials.java.j11;
import com.example.evan.comp296.tutorials.java.j12;
import com.example.evan.comp296.tutorials.java.j13;
import com.example.evan.comp296.tutorials.java.j14;
import com.example.evan.comp296.tutorials.java.j15;
import com.example.evan.comp296.tutorials.java.j2;
import com.example.evan.comp296.tutorials.java.j3;
import com.example.evan.comp296.tutorials.java.j4;
import com.example.evan.comp296.tutorials.java.j5;
import com.example.evan.comp296.tutorials.java.j6;
import com.example.evan.comp296.tutorials.java.j7;
import com.example.evan.comp296.tutorials.java.j8;
import com.example.evan.comp296.tutorials.java.j9;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Evan on 5/15/17.
 */

public class mysql_recycler_adapter extends RecyclerView.Adapter<mysql_recycler_adapter.ViewHolder> {



    Context ctx;


    List<String> list = Collections.emptyList();

    HashMap<Integer,Class> hash = new HashMap<>();


    public mysql_recycler_adapter(List myDataset, Context context) {

        this.list = myDataset;
        this.ctx = context;


    }


    @Override
    public mysql_recycler_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_row_sql, parent, false);
        return new ViewHolder(inflatedView);

    }

    @Override
    public void onBindViewHolder(mysql_recycler_adapter.ViewHolder holder, int position) {

        holder.category.setText(list.get(position));

    }


    @Override
    public int getItemCount() {
        return list.size();
    }






    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView category;


        //4
        public ViewHolder(View v) {
            super(v);


            category = (TextView) v.findViewById(R.id.item_category_sql);


            category.setOnClickListener(this);


        }





        @Override
        public void onClick(View v) {




            //ActivityStarter a = new ActivityStarter();

            int i = v.getId();
            if (i == R.id.item_category_sql) {


                Intent intent = new Intent(v.getContext(), SQL_linked_to_web.class);

                Bundle bundle = new Bundle();

                bundle.putInt("page_num", getLayoutPosition());

                intent.putExtras(bundle);

                v.getContext().startActivity(intent);




            }

        }
    }
}
