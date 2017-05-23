package com.example.evan.comp296;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Collections;
import java.util.List;

import com.example.evan.comp296.tutorials.assembly.MainActivity_pixel;
import com.example.evan.comp296.tutorials.java.java_main;
import com.example.evan.comp296.tutorials.mysql.SQL_main;

/**
 * Created by Evan on 5/6/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PhotoHolder>{

    Uri java, sql,c,assembly;
    private List mDataset;

    int a1,b1,c1,d1;

    int [] categories = new int[4];

    Context ctx;



    List<Data> list = Collections.emptyList();

    public RecyclerAdapter(List myDataset, Context context) {

        this.list = myDataset;
        this.ctx = context;


    }

    @Override
    public RecyclerAdapter.PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_row, parent, false);
        return new PhotoHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.PhotoHolder holder, int position) {


        holder.category.setImageResource(list.get(position).imageId);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }






    public class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView java_item;
        private ImageView mysql_item;
        private ImageView c_plus_plus_item;
        private ImageView assembly_item;
        private ImageView category;
        //private Photo mPhoto;
        //Context ctx;





        //3
        //private static final String PHOTO_KEY = "PHOTO";

        //4
        public PhotoHolder(View v) {
            super(v);

            /*
            java_item = (ImageView) v.findViewById(R.id.item_image_java);
            mysql_item = (ImageView) v.findViewById(R.id.item_image_mysql);
            c_plus_plus_item = (ImageView) v.findViewById(R.id.item_image_c_plus_plus);
            assembly_item = (ImageView) v.findViewById(R.id.item_image_assembly);

            */
            category = (ImageView) v.findViewById(R.id.item_category);



            category.setOnClickListener(this);


            /*

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG", "Element " + getItemCount() + " clicked.");

                    ActivityStarter a = new ActivityStarter();
                    int i = v.getId();
                    if (i == R.id.item_category) {

                        Snackbar.make(v, "Email app to a friend", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                        a.startActivity();
                    }

                }

            });

            */


        }



        @Override
        public void onClick(View v) {


            ActivityStarter a = new ActivityStarter();
            int i = v.getId();
            if (i == R.id.item_category) {
                if (getLayoutPosition() == 0) {
                    Snackbar.make(v, "Email app to a friend", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    //startActivity(new Intent(PhotoHolder.this, MainHomeScreen.class));

                    //startActivity(new Intent(ctx, java_main.class));

                    v.getContext().startActivity(new Intent(v.getContext(),java_main.class));

                    //a.startActivity();
                } else if (getLayoutPosition() == 1) {

                    v.getContext().startActivity(new Intent(v.getContext(),SQL_main.class));
                    //Snackbar.make(v, "Layout position 2", Snackbar.LENGTH_LONG)
                            //.setAction("Action", null).show();

                } else if (getLayoutPosition() == 2) {
                    v.getContext().startActivity(new Intent(v.getContext(),java_main.class));

                } else if (getLayoutPosition() == 3) {


                    v.getContext().startActivity(new Intent(v.getContext(),MainActivity_pixel.class));
                    //Snackbar.make(v, "Layout position 4", Snackbar.LENGTH_LONG)
                            //.setAction("Action", null).show();

                } else if (getLayoutPosition() == 4) {


                    v.getContext().startActivity(new Intent(v.getContext(),MainActivity_pixel.class));
                    //Snackbar.make(v, "Layout position 4", Snackbar.LENGTH_LONG)
                    //.setAction("Action", null).show();

                }

            }
        }


    }


}
