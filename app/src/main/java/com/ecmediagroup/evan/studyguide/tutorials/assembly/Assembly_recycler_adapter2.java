package com.ecmediagroup.evan.studyguide.tutorials.assembly;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ecmediagroup.evan.studyguide.R;

import java.util.List;

/**
 * Created by Evan on 5/24/17.
 */

public class Assembly_recycler_adapter2 extends RecyclerView.Adapter<Assembly_recycler_adapter2.ViewHolder> {


    Context ctx;


    List<String> list;

    //HashMap<Integer,Class> hash = new HashMap<>();


    public Assembly_recycler_adapter2(List myDataset, Context context) {

        this.list = myDataset;
        this.ctx = context;


    }


    @Override
    public Assembly_recycler_adapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_layout_assembly, parent, false);
        return new Assembly_recycler_adapter2.ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(Assembly_recycler_adapter2.ViewHolder holder, int position) {
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


            category = (TextView) v.findViewById(R.id.assembly_topics_list);


            category.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {


            //ActivityStarter a = new ActivityStarter();

            int i = v.getId();
            if (i == R.id.assembly_topics_list) {


                Intent intent = new Intent(v.getContext(), Comp135_operating_systems.class);

                Bundle bundle = new Bundle();

                bundle.putInt("page_num", getLayoutPosition());

                intent.putExtras(bundle);

                v.getContext().startActivity(intent);


            }

        }


    }
}
