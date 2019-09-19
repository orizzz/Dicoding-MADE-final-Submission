package com.gregetdev.oris.dicodingsubmission.fragment.favorite;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gregetdev.oris.dicodingsubmission.R;
import com.gregetdev.oris.dicodingsubmission.activity.Detail.FavMovieDetailActivity;
import com.gregetdev.oris.dicodingsubmission.adapter.FavMovieAdapter;
import com.gregetdev.oris.dicodingsubmission.db.FavoriteHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavMovieFragment extends Fragment{
    private RecyclerView recyclerView;
    private TextView EmptyText;
    private ProgressBar progressBar;
    private FavMovieAdapter adapter;

    private FavoriteHelper favoriteHelper;


    public FavMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_fav_movie);
        progressBar = view.findViewById(R.id.Fav_MovieSprogressBar);
        EmptyText = view.findViewById(R.id.empty_fav_movie);

        favoriteHelper = FavoriteHelper.getInstance(getContext());
        favoriteHelper.open();

        showList();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == FavMovieDetailActivity.RESULT_DELETE) {
            int position = data.getIntExtra(FavMovieDetailActivity.EXTRA_POSITION, 0);
            adapter.removeItem(position);
        }
    }

    private void showList() {
        showLoading(true);
        SetAdapter();
    }

    private void SetAdapter() {
        adapter = new FavMovieAdapter();
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setData(favoriteHelper.setAllMovies(),"Movies",getActivity());
        ShowText();
        showLoading(false);
    }

    private void ShowText() {
        if (favoriteHelper.setAllMovies().isEmpty()){
            EmptyText.setVisibility(View.VISIBLE);
        }else {
            EmptyText.setVisibility(View.GONE);
        }
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
