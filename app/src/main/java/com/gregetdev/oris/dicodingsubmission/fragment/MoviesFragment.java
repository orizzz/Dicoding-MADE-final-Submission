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
import com.gregetdev.oris.dicodingsubmission.model.Movie.MovieModel;
import com.gregetdev.oris.dicodingsubmission.model.Movie.RetriveMovieDataModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private List<MovieModel> movieList;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeContainer;
    private RetriveMovieDataModel retriveDataModel;


    public MoviesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        retriveDataModel = ViewModelProviders.of(MoviesFragment.this).get(RetriveMovieDataModel.class);

        progressBar = view.findViewById(R.id.MovieSprogressBar);
        recyclerView = view.findViewById(R.id.movies_list_rv);
        showList();

        swipeContainer = view.findViewById(R.id.SwipeMovie);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showList();
                showLoading(false);
                Toast.makeText(getActivity(),"LIST REFRESHED",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void showList() {
        showLoading(true);
        retriveDataModel.setMovieList(getActivity());
        SetAdapter();
    }

    private void SetAdapter() {

        retriveDataModel.getMovieslist().observe(MoviesFragment.this, loadMovies);

        adapter = new MovieAdapter();
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

    }

    private Observer<List<MovieModel>> loadMovies = new Observer<List<MovieModel>>() {
        @Override
        public void onChanged(@Nullable List<MovieModel> movieModels) {
            adapter.setData(movieModels,"Movies",getActivity());
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
