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
import android.widget.TextView;
import android.widget.Toast;

import com.gregetdev.oris.dicodingsubmission.R;
import com.gregetdev.oris.dicodingsubmission.adapter.MovieAdapter;
import com.gregetdev.oris.dicodingsubmission.adapter.TvAdapter;
import com.gregetdev.oris.dicodingsubmission.model.Movie.MovieModel;
import com.gregetdev.oris.dicodingsubmission.model.Movie.RetriveMovieDataModel;
import com.gregetdev.oris.dicodingsubmission.model.tv.RetriveTvDataModel;
import com.gregetdev.oris.dicodingsubmission.model.tv.TvModel;

import java.util.List;

public class SearchFragment extends Fragment {
    private static final String QUERY = "param1";
    private static final String TAB = "param2";
    private String query;
    private String tab;

    TextView emptySearch;

    private RecyclerView recyclerView;
    private MovieAdapter adapterMovies;
    private TvAdapter adapterTV;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeContainer;
    private RetriveMovieDataModel retriveMovieDataModel;
    private RetriveTvDataModel retriveTvDataModel;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(String Query,String Tab) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(QUERY, Query);
        args.putString(TAB, Tab);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            query = getArguments().getString(QUERY);
            tab = getArguments().getString(TAB);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emptySearch = view.findViewById(R.id.search_empty_text);

        if (tab.equals("Movies")){
            retriveMovieDataModel = ViewModelProviders.of(SearchFragment.this).get(RetriveMovieDataModel.class);
        }else if (tab.equals("Tv")){
            retriveTvDataModel = ViewModelProviders.of(SearchFragment.this).get(RetriveTvDataModel.class);
        }

        progressBar = view.findViewById(R.id.SearchSprogressBar);
        recyclerView = view.findViewById(R.id.search_list_rv);
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
        if (tab.equals("Movies")){
            retriveMovieDataModel.SearchMovie(getActivity(),query);
        }else if (tab.equals("Tv")){
            retriveTvDataModel.SearchTv(getActivity(),query);
        }
        SetAdapter();
    }

    private void SetAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (tab.equals("Movies")){
            retriveMovieDataModel.getMovieslist().observe(SearchFragment.this, loadMovies);
            adapterMovies = new MovieAdapter();
            adapterMovies.notifyDataSetChanged();
            recyclerView.setAdapter(adapterMovies);
        }else if (tab.equals("Tv")){
            retriveTvDataModel.getTvlist().observe(SearchFragment.this, loadTv);
            adapterTV = new TvAdapter();
            adapterTV.notifyDataSetChanged();
            recyclerView.setAdapter(adapterTV);
        }

    }

    private void ShowText() {
        int size = 0 ;
        if (tab.equals("Movies")){
            size = retriveMovieDataModel.getMovieslist().getValue().size();
        }else if (tab.equals("Tv")){
            size = retriveTvDataModel.getTvlist().getValue().size();
        }
        if (size == 0){
            emptySearch.setVisibility(View.VISIBLE);
        }else {
            emptySearch.setVisibility(View.GONE);
        }
    }

    private Observer<List<MovieModel>> loadMovies = new Observer<List<MovieModel>>() {
        @Override
        public void onChanged(@Nullable List<MovieModel> movieModels) {
            adapterMovies.setData(movieModels,"Movies",getActivity());
            showLoading(false);
            ShowText();
            if (swipeContainer.isRefreshing()){
                swipeContainer.setRefreshing(false);
            }
        }
    };

    private Observer<List<TvModel>> loadTv = new Observer<List<TvModel>>() {
        @Override
        public void onChanged(@Nullable List<TvModel> Models) {
            adapterTV.setData(Models,"Tv",getActivity());
            showLoading(false);
            ShowText();
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
