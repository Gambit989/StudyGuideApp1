package com.example.evan.comp296.tutorials.html_css.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.evan.comp296.Data;
import com.example.evan.comp296.Header_html;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.example.evan.comp296.tutorials.html_css.TestRecyclerViewAdapter;
import com.example.evan.comp296.R;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class RecyclerViewFragment extends Fragment {

    private static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 10;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        final List<Object> items = new ArrayList<>();

            items.add(new Header_html(R.drawable.html5));
            items.add(new Data("hello"));
            items.add(new Data("hello"));
            items.add(new Data("hello"));
            items.add(new Data("hello"));
            items.add(new Data("hello"));



        //setup materialviewpager

        if (GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setHasFixedSize(true);

        //Use this now
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mRecyclerView.setAdapter(new TestRecyclerViewAdapter(items, getContext()));
    }
}
