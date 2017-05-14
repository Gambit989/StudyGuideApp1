package com.example.evan.comp296.tutorials.java;

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
import android.widget.Spinner;

import com.example.evan.comp296.Data;
import com.example.evan.comp296.Notes.Note_drv;
import com.example.evan.comp296.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evan on 5/7/17.
 */

public class java_main extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner java_spinner;

    RecyclerView java_RecyclerView1;

    LinearLayoutManager mLinearLayoutManager;
    LinearLayoutManager mLinearLayoutManager2;
    LinearLayoutManager mLinearLayoutManager3;
    LinearLayoutManager mLinearLayoutManager4;

    Java_Recycler_Adapter mAdapter;
    Java_Recycler_Adapter mAdapter1;
    Java_Recycler_Adapter2 mAdapter2;
    Java_Recycler_Adapter3 mAdapter3;


    List data;
    List data2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.java_main);


        java_spinner = (Spinner) findViewById(R.id.spinner_java);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.java_classes, R.layout.spinner_text);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        // Apply the adapter to the spinner
        java_spinner.setAdapter(adapter);


        java_spinner.setOnItemSelectedListener(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab4);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(java_main.this, Note_drv.class));

                /*

                Snackbar.make(view, "Email app to a friend", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                 */
            }
        });



        data = fill_with_data();

        data2=fill_with_data3();

        java_RecyclerView1 = (RecyclerView) findViewById(R.id.java_recycler);
        mLinearLayoutManager = new LinearLayoutManager(this);
        java_RecyclerView1.setLayoutManager(mLinearLayoutManager);
        mAdapter = new Java_Recycler_Adapter(data2, getApplicationContext());
        java_RecyclerView1.setAdapter(mAdapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String selected = parent.getItemAtPosition(position).toString();



        if (position==1) {



            mAdapter1 = new Java_Recycler_Adapter(fill_with_data(), getApplicationContext());


            mLinearLayoutManager2 = new LinearLayoutManager(this);
            java_RecyclerView1.setLayoutManager(mLinearLayoutManager2);

            java_RecyclerView1.setAdapter(mAdapter1);

        } else if (position==2) {

            mAdapter2 = new Java_Recycler_Adapter2(fill_with_data2(), getApplicationContext());


            mLinearLayoutManager3 = new LinearLayoutManager(this);
            java_RecyclerView1.setLayoutManager(mLinearLayoutManager3);

            java_RecyclerView1.setAdapter(mAdapter2);

        } else if (position==3) {

            mAdapter3 = new Java_Recycler_Adapter3(fill_with_data4(), getApplicationContext());


            mLinearLayoutManager4 = new LinearLayoutManager(this);
            java_RecyclerView1.setLayoutManager(mLinearLayoutManager4);

            java_RecyclerView1.setAdapter(mAdapter3);

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
        data.add(new Data("Numbers"));
        data.add(new Data("Chars"));
        data.add(new Data("Strings"));
        data.add(new Data("Arrays"));
        data.add(new Data("RegEx"));
        data.add(new Data("Methods"));
        data.add(new Data("File I/O"));


        return data;
    }


    public List fill_with_data2() {

        List<Data> data = new ArrayList();


        data.add(new Data("Intro to Data Structures"));
        data.add(new Data("Stacks"));
        data.add(new Data("Queue"));
        data.add(new Data("Linked List"));

        return data;
    }


    public List fill_with_data3() {

        List<Data> data = new ArrayList();


        return data;
    }


    public List fill_with_data4() {

        List<Data> data = new ArrayList();


        data.add(new Data("Inheritence"));
        data.add(new Data("Polymorphism"));
        data.add(new Data("Abstraction"));
        data.add(new Data("Enapsulation"));
        data.add(new Data("Overriding"));
        data.add(new Data("Interfaces"));
        data.add(new Data("Packages"));


        return data;
    }






}
