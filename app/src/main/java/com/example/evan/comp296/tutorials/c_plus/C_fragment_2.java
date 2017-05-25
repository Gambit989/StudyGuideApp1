package com.example.evan.comp296.tutorials.c_plus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.evan.comp296.Notes_main.Note_drv;
import com.example.evan.comp296.R;
import com.example.evan.comp296.tutorials.mysql.mysql_recycler_adapter_2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evan on 5/22/17.
 */

public class C_fragment_2 extends Fragment {

    RecyclerView mysql_recycler;
    mysql_recycler_adapter_2 mAdapter2;
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



        mAdapter2 = new mysql_recycler_adapter_2(fill_with_data4(), view.getContext());


        mLinearLayoutManager = new LinearLayoutManager(view.getContext());
        mysql_recycler.setLayoutManager(mLinearLayoutManager);

        mysql_recycler.setAdapter(mAdapter2);



        return  view;

    }



    public List fill_with_data4() {

        List<String> data = new ArrayList();


        data.add("Will be added next update!");


        return data;
    }




}

