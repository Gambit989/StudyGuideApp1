package com.ecmediagroup.evan.studyguide.Notes_main;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.ecmediagroup.evan.studyguide.Data;
import com.ecmediagroup.evan.studyguide.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evan on 5/10/17.
 */

public class Note_Homepage extends AppCompatActivity {


    Note_database nd;



    RecyclerView Note_RecyclerView1;

    LinearLayoutManager mLinearLayoutManager;
    LinearLayoutManager mLinearLayoutManager2;

    Note_Recycler_Adapter mAdapter;
    Note_Recycler_Adapter mAdapter1;

    List data;
    List data2;

    //Button refresh_button;
    String[] test;

    Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_homepage);



        toolbar= (Toolbar) findViewById(R.id.toolbar_notes);

        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.ic_note_2);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab6);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Note_Homepage.this, Note_drv.class));

                /*

                Snackbar.make(view, "Email app to a friend", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                 */
            }
        });





        //refresh_button = (Button) findViewById(R.id.refresh);
        nd = new Note_database(Note_Homepage.this);


        Toast.makeText(this, nd.getTitles().toString(),Toast.LENGTH_LONG);


        //data = nd.getText_data();

        data = nd.getTitles();

        //data2=fill_with_data2();

        Note_RecyclerView1 = (RecyclerView) findViewById(R.id.notes_recycler);
        mLinearLayoutManager = new LinearLayoutManager(this);
        Note_RecyclerView1.setLayoutManager(mLinearLayoutManager);
        mAdapter = new Note_Recycler_Adapter(data, getApplicationContext());
        Note_RecyclerView1.setAdapter(mAdapter);


        /*
        refresh_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });

        */

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_recycler_view);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                finish();
                startActivity(getIntent());


            }
        });


    }





    public List fill_with_data2() {

        List<Data> data = new ArrayList();



        data.add(new Data(nd.getTitles()));


        /*

        data = nd.getTitles();

        for (String a : data) {
            int i=0;
            test[0]=a;
            i++;
        }

        */

        //data.add("hello");


        return data;
    }













}
