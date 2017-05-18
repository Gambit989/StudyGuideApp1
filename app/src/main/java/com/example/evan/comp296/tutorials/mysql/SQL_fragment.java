package com.example.evan.comp296.tutorials.mysql;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.evan.comp296.Data;
import com.example.evan.comp296.Notes.Note_drv;
import com.example.evan.comp296.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evan on 5/14/17.
 */

public class SQL_fragment extends android.support.v4.app.Fragment {



    RecyclerView mysql_recycler;
    mysql_recycler_adapter mAdapter1;
    LinearLayoutManager mLinearLayoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=  inflater.inflate(R.layout.fragment_test, container, false);



        mysql_recycler = (RecyclerView) view.findViewById(R.id.mysql_recycler);


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab10);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(view.getContext(), Note_drv.class));


            }
        });



        mAdapter1 = new mysql_recycler_adapter(fill_with_data4(), view.getContext());


        mLinearLayoutManager = new LinearLayoutManager(view.getContext());
        mysql_recycler.setLayoutManager(mLinearLayoutManager);

        mysql_recycler.setAdapter(mAdapter1);



        return  view;

    }



    public List fill_with_data4() {

        List<String> data = new ArrayList();


        data.add("Introduction");
        data.add("RDBMS Concepts");
        data.add("Databases");
        data.add("Syntax");
        data.add("Data Types");
        data.add("Operators");
        data.add("Expressions");
        data.add("Databases");
        data.add("Create Database");
        data.add("Drop Database");
        data.add("Select Databsse");
        data.add("Create Table");
        data.add("Drop Table");
        data.add("Insert Query");
        data.add("Select Query");
        data.add("Where Clause");
        data.add("AND & OR Clauses");
        data.add("Update Query");
        data.add("Delete Query");
        data.add("Like Clause");
        data.add("Order By");
        data.add("Group By");
        data.add("Distinct keywword");
        data.add("Sorting results");


        return data;
    }
}
