package com.example.evan.comp296.calendar_events;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.evan.comp296.Notes.Note_Recycler_Adapter;
import com.example.evan.comp296.Notes.Note_database;
import com.example.evan.comp296.Notes.Note_delete_view;
import com.example.evan.comp296.Notes.Note_viewer;
import com.example.evan.comp296.R;
import com.example.evan.comp296.tutorials.java.j0;
import com.example.evan.comp296.tutorials.java.j1;
import com.example.evan.comp296.tutorials.java.j2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Evan on 5/21/17.
 */

public class Event_Recycler_Adapter extends RecyclerView.Adapter<Event_Recycler_Adapter.ViewHolder> {



    Context ctx;


    List<String> list = Collections.emptyList();
    List<String> list2 = Collections.emptyList();
    List<String> list3 = Collections.emptyList();

    HashMap<Integer,Class> hash = new HashMap<>();

    String[] array_from_list;


    public Event_Recycler_Adapter(List myDataset, List myDataset2, List myDataset3, Context context) {

        this.list = myDataset;
        this.list2 = myDataset2;
        this.list3  = myDataset3;
        this.ctx = context;


    }

    @Override
    public Event_Recycler_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_row_calendar, parent, false);
        return new Event_Recycler_Adapter.ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (list.get(position) != null) {
            holder.title.setText(list.get(position));
            holder.date.setText(list2.get(position));
            holder.time.setText(list3.get(position));
        } else {
            return;
        }




    }


    @Override
    public int getItemCount() {
        return list.size();
    }






    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener , View.OnLongClickListener{

        private TextView title;
        private TextView date;
        private TextView time;
        private ImageView image_button;






        //4
        public ViewHolder(View v) {
            super(v);


            title = (TextView) v.findViewById(R.id.calendar_event_title2);
            date = (TextView) v.findViewById(R.id.calendar_date2);
            time = (TextView) v.findViewById(R.id.calendar_time2);

            image_button = (ImageView) v.findViewById(R.id.imageButton_event);

            //LinearLayout calendar = (LinearLayout) v.findViewById(R.id.calendar_Container);


            image_button.setOnClickListener(this);

            title.setOnLongClickListener(this);


        }




        @Override
        public void onClick(View v) {

            //ActivityStarter a = new ActivityStarter();

            int i = v.getId();

            if (i == R.id.imageButton_event) {

                String title = list.get(getLayoutPosition());


                //Intent mIntent2 = new Intent(v.getContext(), Note_viewer.class);
                //mIntent2.putExtra("position", getAdapterPosition());

                int x=getAdapterPosition();

                Intent mIntent = new Intent(v.getContext(), event_delete_view.class);
                mIntent.putExtra("title", title);

                v.getContext().startActivity(mIntent);

                Log.d("TAG","********** IMAGE BUTTON BLICKED, position is " + x + "***** Title =" +title+ "*****");


            }

        }

        @Override
        public boolean onLongClick(View v) {


            int i = v.getId();

            if (i == R.id.calendar_event_title2) {


                String title = list.get(getLayoutPosition());


                //Intent mIntent2 = new Intent(v.getContext(), Note_viewer.class);
                //mIntent2.putExtra("position", getAdapterPosition());

                int position=getAdapterPosition();



                Intent mIntent = new Intent(v.getContext(), event_delete_view.class);
                mIntent.putExtra("title", title);

                v.getContext().startActivity(mIntent);

                Log.d("TAG","**********" + position + "***** Title =" +title+ "*****");




                /*
                list.remove(position);
                list2.remove(position);
                list3.remove(position);
                notifyItemRemoved(position);
                Log.d("TAG","**********" + "list " +position + " removed ***** Title =" +title+ "*****");
                */


            } else if (i==R.id.calendar_date2 ) {

                String title = list.get(getLayoutPosition());


                //Intent mIntent2 = new Intent(v.getContext(), Note_viewer.class);
                //mIntent2.putExtra("position", getAdapterPosition());

                int x=getAdapterPosition();

                Intent mIntent = new Intent(v.getContext(), event_delete_view.class);
                mIntent.putExtra("title", title);

                v.getContext().startActivity(mIntent);

                Log.d("TAG","**********" + x + "*****");

            }else if (i==R.id.calendar_time2) {

                String title = list.get(getLayoutPosition());


                //Intent mIntent2 = new Intent(v.getContext(), Note_viewer.class);
                //mIntent2.putExtra("position", getAdapterPosition());

                int x=getAdapterPosition();

                Intent mIntent = new Intent(v.getContext(), event_delete_view.class);
                mIntent.putExtra("title", title);

                v.getContext().startActivity(mIntent);

                Log.d("TAG","**********" + x + "*****");


            }



            return false;
        }
    }
}