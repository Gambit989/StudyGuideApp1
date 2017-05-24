package com.example.evan.comp296.calendar_events;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.evan.comp296.Notes_main.Note_database;
import com.example.evan.comp296.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Evan on 5/21/17.
 */

public class calendar_viewer extends AppCompatActivity {

    Button add_event;
    RecyclerView event_recycler;
    CollapsingToolbarLayout mCollapsingToolbar;

    List data_title, data_date, data_time;

    Event_Recycler_Adapter mAdapter;
    LinearLayoutManager mLinearLayoutManager;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);

        //setContentView(R.layout.calendar_example_layout);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);



        event_recycler = (RecyclerView) findViewById(R.id.events_recyclerview);

        //event_recycler = (RecyclerView) findViewById(R.id.events_Recyclerview_2);
        add_event = (Button) findViewById(R.id.button_add_event);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab12);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent browserIntent = new Intent(calendar_viewer.this, calendar_main.class);
                startActivity(browserIntent);
            }
        });



        Note_database nd = new Note_database(calendar_viewer.this);

        /*

        if (nd.Event_ID_Exists(1)) {

            data_title = nd.getEventTitles();
            data_date = nd.getEventDate();
            data_time = nd.getEventTime();


        } else {

            data_title= Collections.emptyList();
            data_date = Collections.emptyList();
            data_time = Collections.emptyList();
        }

        */


        data_title= Collections.emptyList();
        data_date = Collections.emptyList();
        data_time = Collections.emptyList();


        data_title = nd.getEventTitles();
        data_date = nd.getEventDate();
        data_time = nd.getEventTime();



        //Button button = (Button) findViewById(R.id.button_event_test);
        //Button refresh = (Button) findViewById(R.id.button_event_test_refresh);


        /*

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(calendar_viewer.this, calendar_main.class));
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });

        */


        mLinearLayoutManager = new LinearLayoutManager(this);
        event_recycler.setLayoutManager(mLinearLayoutManager);
        mAdapter = new Event_Recycler_Adapter(data_title, data_date, data_time, getApplicationContext());
        event_recycler.setAdapter(mAdapter);




        add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), calendar_main.class));
            }
        });



        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_recycler_view2);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                finish();
                startActivity(getIntent());


            }
        });




    }


    @Override
    protected void onResume() {
        super.onResume();



        for (int i=0; i <data_title.size(); i++) {

            System.out.println("************"+ data_title.get(i)+" ***********");
        }


        for (int i=0; i <data_date.size(); i++) {

            System.out.println("************"+ data_date.get(i)+" ***********");
        }

        for (int i=0; i <data_time.size(); i++) {

            System.out.println("************"+ data_time.get(i)+" ***********");
        }


    }
}
