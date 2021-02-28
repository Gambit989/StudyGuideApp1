package com.ecmediagroup.evan.studyguide.tutorials.html_css.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecmediagroup.evan.studyguide.Data;
import com.ecmediagroup.evan.studyguide.Header_html;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.ecmediagroup.evan.studyguide.tutorials.html_css.TestRecyclerViewAdapter;
import com.ecmediagroup.evan.studyguide.R;


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
            items.add(new Data("Introduction"));
            items.add(new Data("Useful Guide"));
            items.add(new Data("Basic Tags"));
            items.add(new Data("Elements"));
            items.add(new Data("Attributes"));
            items.add(new Data("Formatting"));
            items.add(new Data("Phrase tags"));
            items.add(new Data("Meta Tags"));
            items.add(new Data("Comments"));
            items.add(new Data("Images"));
            items.add(new Data("Tables"));
            items.add(new Data("Lists"));
            items.add(new Data("Text Links"));
            items.add(new Data("Image Links"));
            items.add(new Data("Email Links"));
            items.add(new Data("Frames"));
            items.add(new Data("IFrames"));
            items.add(new Data("Blocks"));
            items.add(new Data("backgrounds"));
            items.add(new Data("Fonts"));
            items.add(new Data("Forms"));
            items.add(new Data("Embed Multimedia"));
            items.add(new Data("Marquees"));
            items.add(new Data("Header"));
            items.add(new Data("Style Sheet"));
            items.add(new Data("Javascript"));
            items.add(new Data("Layouts"));




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
