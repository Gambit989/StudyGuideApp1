package com.example.evan.comp296.tutorials.mysql;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evan.comp296.R;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Evan on 5/19/17.
 */

public class mysql_recycler_adapter_2 extends RecyclerView.Adapter<mysql_recycler_adapter_2.ViewHolder> {

    Context ctx;


    List<String> list = Collections.emptyList();

    HashMap<Integer,Class> hash = new HashMap<>();


    public mysql_recycler_adapter_2(List myDataset, Context context) {

        this.list = myDataset;
        this.ctx = context;


    }


    @Override
    public mysql_recycler_adapter_2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_row_sql_2, parent, false);
        return new ViewHolder(inflatedView);

    }

    @Override
    public void onBindViewHolder(mysql_recycler_adapter_2.ViewHolder holder, int position) {

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


                Intent intent = new Intent(v.getContext(), SQL_linked_to_web_2.class);

                Bundle bundle = new Bundle();

                bundle.putInt("page_num", getLayoutPosition());

                intent.putExtras(bundle);

                v.getContext().startActivity(intent);




            }

        }
    }
}
