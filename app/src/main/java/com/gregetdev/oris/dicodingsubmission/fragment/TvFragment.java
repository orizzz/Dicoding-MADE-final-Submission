package com.gregetdev.oris.dicodingsubmission.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gregetdev.oris.dicodingsubmission.R;
import com.gregetdev.oris.dicodingsubmission.adapter.MovieAdapter;
import com.gregetdev.oris.dicodingsubmission.adapter.TvAdapter;
import com.gregetdev.oris.dicodingsubmission.model.Movie.MovieModel;
import com.gregetdev.oris.dicodingsubmission.model.tv.RetriveTvDataModel;
import com.gregetdev.oris.dicodingsubmission.model.tv.TvModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment {

    private RecyclerView recyclerView;
    private TvAdapter adapter;
    private List<TvModel> movieList;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeContainer;
    private RetriveTvDataModel retriveTvDataModel;


    public TvFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        retriveTvDataModel = ViewModelProviders.of(TvFragment.this).get(RetriveTvDataModel.class);

        progressBar = view.findViewById(R.id.MovieSprogressBar_TV);
        recyclerView = view.findViewById(R.id.tv_list_rv);
        showList();

        swipeContainer = view.findViewById(R.id.SwipeTv);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showList();
                showLoading(false);
                Toast.makeText(getActivity(),"List Refreshed",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void showList() {
        showLoading(true);
        retriveTvDataModel.setMovieList(getActivity());
        SetAdapter();
    }

    private void SetAdapter() {

        retriveTvDataModel.getTvlist().observe(TvFragment.this, loadMovies);

        adapter = new TvAdapter();
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

    }

    private Observer<List<TvModel>> loadMovies = new Observer<List<TvModel>>() {
        @Override
        public void onChanged(@Nullable List<TvModel> movieModels) {
            adapter.setData(movieModels,"Tv",getActivity());
            showLoading(false);
            if (swipeContainer.isRefreshing()){
                swipeContainer.setRefreshing(false);
            }

        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }


}
