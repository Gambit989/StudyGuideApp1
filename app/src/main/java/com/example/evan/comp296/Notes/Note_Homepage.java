package com.example.evan.comp296.Notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.evan.comp296.Data;
import com.example.evan.comp296.R;
import com.example.evan.comp296.tutorials.java.Java_Recycler_Adapter;
import com.example.evan.comp296.tutorials.java.java_main;

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

    Button refresh_button;
    String[] test;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_homepage);



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





        refresh_button = (Button) findViewById(R.id.refresh);
        nd = new Note_database(Note_Homepage.this);


        Toast.makeText(this, nd.getTitles().toString(),Toast.LENGTH_LONG);


        data = nd.getText_data();

        data2=fill_with_data2();

        Note_RecyclerView1 = (RecyclerView) findViewById(R.id.notes_recycler);
        mLinearLayoutManager = new LinearLayoutManager(this);
        Note_RecyclerView1.setLayoutManager(mLinearLayoutManager);
        mAdapter = new Note_Recycler_Adapter(data, getApplicationContext());
        Note_RecyclerView1.setAdapter(mAdapter);


        refresh_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });


    }



    public List fill_with_data() {

        List<Data> data = new ArrayList();



        data.add(new Data("Intro"));
        data.add(new Data("Basic Syntax"));
        data.add(new Data("Objects and Classes"));
        data.add(new Data("Basic Datatypes"));
        data.add(new Data("Variables"));
        data.add(new Data("Modifier Types"));
        data.add(new Data("Operators"));
        data.add(new Data("Loops"));
        data.add(new Data("Decision Making"));
        data.add(new Data("Chars"));
        data.add(new Data("Strings"));
        data.add(new Data("Arrays"));
        data.add(new Data("RegEx"));
        data.add(new Data("Methods"));
        data.add(new Data("File I/O"));


        return data;
    }


    public List fill_with_data1() {

        List<Data> data = new ArrayList();



        data.add(new Data("ggggg"));
        data.add(new Data("ggsgggn"));
        data.add(new Data("ogtuff"));
        data.add(new Data("tffffff"));
        data.add(new Data("hello"));

        return data;
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
