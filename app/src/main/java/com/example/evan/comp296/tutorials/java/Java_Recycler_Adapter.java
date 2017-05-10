package com.example.evan.comp296.tutorials.java;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evan.comp296.Data;
import com.example.evan.comp296.R;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Evan on 5/8/17.
 */

public class Java_Recycler_Adapter extends RecyclerView.Adapter<Java_Recycler_Adapter.ViewHolder> {



    Context ctx;


    List<Data> list = Collections.emptyList();

    HashMap<Integer,Class> hash = new HashMap<>();


    public Java_Recycler_Adapter(List myDataset, Context context) {

        this.list = myDataset;
        this.ctx = context;


    }


    @Override
    public Java_Recycler_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_layout_java, parent, false);
        return new ViewHolder(inflatedView);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {



        holder.category.setText(list.get(position).java);

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


        public void addClasses(HashMap<Integer,Class> hash) {

            hash.put(0,j0.class);
            hash.put(1,j1.class);
            hash.put(2,j2.class);




        }


        @Override
        public void onClick(View v) {






            addClasses(hash);

            Log.d(" LOG ", hash.get(0).toString());


            //ActivityStarter a = new ActivityStarter();

            int i = v.getId();
            if (i == R.id.java_topics_list) {


                v.getContext().startActivity(new Intent(v.getContext(),hash.get(getLayoutPosition())));








            }

        }
    }
}

