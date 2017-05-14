package com.example.evan.comp296.Notes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evan.comp296.Data;
import com.example.evan.comp296.R;
import com.example.evan.comp296.tutorials.java.Java_Recycler_Adapter;
import com.example.evan.comp296.tutorials.java.j0;
import com.example.evan.comp296.tutorials.java.j1;
import com.example.evan.comp296.tutorials.java.j2;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Evan on 5/10/17.
 */

public class Note_Recycler_Adapter extends RecyclerView.Adapter<Note_Recycler_Adapter.ViewHolder>{


    Context ctx;


    List<String> list = Collections.emptyList();

    HashMap<Integer,Class> hash = new HashMap<>();

    String[] array_from_list;


    public Note_Recycler_Adapter(List myDataset, Context context) {

        this.list = myDataset;
        this.ctx = context;


    }


    @Override
    public Note_Recycler_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_layout_notes, parent, false);
        return new ViewHolder(inflatedView);

    }

    @Override
    public void onBindViewHolder(Note_Recycler_Adapter.ViewHolder holder, int position) {



        holder.category.setText(list.get(position));

    }



    @Override
    public int getItemCount() {
        return list.size();
    }






    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener , View.OnLongClickListener{

        private TextView category;






        //4
        public ViewHolder(View v) {
            super(v);


            category = (TextView) v.findViewById(R.id.notes_title_list);


            category.setOnClickListener(this);

            category.setOnLongClickListener(this);


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
            if (i == R.id.notes_title_list) {

                Note_database db = new Note_database(v.getContext());

                //String note_text = db.getNote_Text(getAdapterPosition());

                String title = list.get(getLayoutPosition());

                String note = db.getNote_Text(title);


                Intent mIntent = new Intent(v.getContext(), Note_viewer.class);
                mIntent.putExtra("note", note);

                v.getContext().startActivity(mIntent);








            }

        }

        @Override
        public boolean onLongClick(View v) {


            int i = v.getId();
            if (i == R.id.notes_title_list) {


                String title = list.get(getLayoutPosition());


                //Intent mIntent2 = new Intent(v.getContext(), Note_viewer.class);
                //mIntent2.putExtra("position", getAdapterPosition());

                int x=getAdapterPosition();

                Intent mIntent = new Intent(v.getContext(), Note_delete_view.class);
                mIntent.putExtra("title", title);

                v.getContext().startActivity(mIntent);

                Log.d("TAG","**********" + x + "*****");








            }










            return false;
        }
    }
}
