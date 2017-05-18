package com.example.evan.comp296.tutorials.assembly;

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
import java.util.List;

/**
 * Created by Evan on 5/17/17.
 */

public class Assembly_recycler_adapter extends RecyclerView.Adapter<Assembly_recycler_adapter.ViewHolder> {


    Context ctx;


    List<String> list;

    //HashMap<Integer,Class> hash = new HashMap<>();


    public Assembly_recycler_adapter(List myDataset, Context context) {

        this.list = myDataset;
        this.ctx = context;


    }


    @Override
    public Assembly_recycler_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_layout_java, parent, false);
        return new Assembly_recycler_adapter.ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(Assembly_recycler_adapter.ViewHolder holder, int position) {
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


            category = (TextView) v.findViewById(R.id.java_topics_list);


            category.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {


            //ActivityStarter a = new ActivityStarter();

            int i = v.getId();
            if (i == R.id.java_topics_list) {


                Intent intent = new Intent(v.getContext(), Comp135.class);

                Bundle bundle = new Bundle();

                bundle.putInt("page_num", getLayoutPosition());

                intent.putExtras(bundle);

                v.getContext().startActivity(intent);


            }

        }


    }
}
