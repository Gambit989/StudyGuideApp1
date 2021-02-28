package com.ecmediagroup.evan.studyguide.tutorials.java;

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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Evan on 5/13/17.
 */

public class Java_Recycler_Adapter3 extends RecyclerView.Adapter<Java_Recycler_Adapter3.ViewHolder> {




    Context ctx;


    List<Data> list = Collections.emptyList();

    HashMap<Integer,Class> hash = new HashMap<>();


    public Java_Recycler_Adapter3(List myDataset, Context context) {

        this.list = myDataset;
        this.ctx = context;


    }



    @Override
    public Java_Recycler_Adapter3.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_layout_java_2, parent, false);
        return new Java_Recycler_Adapter3.ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(Java_Recycler_Adapter3.ViewHolder holder, int position) {

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


            category = (TextView) v.findViewById(R.id.java_topics_list_2);


            category.setOnClickListener(this);


        }




        @Override
        public void onClick(View v) {


            //ActivityStarter a = new ActivityStarter();

            int i = v.getId();
            if (i == R.id.java_topics_list) {





                Intent intent = new Intent(v.getContext(),comp271.class);

                Bundle bundle = new Bundle();

                bundle.putInt("page_num", getLayoutPosition());

                intent.putExtras(bundle);

                v.getContext().startActivity(intent);



                /*


                comp271 comp = new comp271(getLayoutPosition());

                Intent id = new Intent(v.getContext(), comp.getClass());
                Log.d("TAG", "****LAYOUT POSITION "+getLayoutPosition()+" ******");

                v.getContext().startActivity(id);


                //v.getContext().startActivity(new Intent(v.getContext(),hash.get(getLayoutPosition())));


                 */

            }

        }
    }





}
