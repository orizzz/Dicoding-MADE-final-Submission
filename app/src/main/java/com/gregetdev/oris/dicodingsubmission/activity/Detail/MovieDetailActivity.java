package com.gregetdev.oris.dicodingsubmission.activity.Detail;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gregetdev.oris.dicodingsubmission.R;
import com.gregetdev.oris.dicodingsubmission.model.FavoriteModel;
import com.gregetdev.oris.dicodingsubmission.model.Movie.MovieModel;
import com.gregetdev.oris.dicodingsubmission.db.FavoriteHelper;


import static com.gregetdev.oris.dicodingsubmission.api.Client.IMAGE_URL;

public class MovieDetailActivity extends AppCompatActivity {


    private String Intent_code = "MovieDetail";
    private String Intent_show = "Show";

    private FloatingActionButton fab_fav;
    private FloatingActionButton fab_del;
    TextView title, vote,desc,date;
    ImageView img;

    private MovieModel Movies;
    private FavoriteHelper favoriteHelper;
    String Show;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        fab_fav = findViewById(R.id.FAB_add_favorite_movie);

        Show = getIntent().getStringExtra(Intent_show);
        Movies = getIntent().getParcelableExtra(Intent_code);

        title = findViewById(R.id.detail_judul_film);
        vote = findViewById(R.id.detail_vote_film);
        desc = findViewById(R.id.detail_desc_film);
        img = findViewById(R.id.detail_image_film);
        date = findViewById(R.id.detail_date_film);

        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
        favoriteHelper.open();

        ShowData();

        fab_fav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AddFavorite(view);
            }
        });

    }

    private void ShowData() {
        title.setText(Movies.getmTitle());
        vote.setText(String.valueOf(Movies.getmVoteAverage()));
        desc.setText(Movies.getmOverview());
        date.setText(Movies.getmReleaseDate());
        Glide.with(MovieDetailActivity.this)
                .load(IMAGE_URL+Movies.getmPosterPath())
                .into(img);
    }

    private void AddFavorite(View view) {
        FavoriteModel FavModel = new FavoriteModel();
        FavModel.setID(Movies.getmId());
        FavModel.setTitle(Movies.getmTitle());
        FavModel.setOverview(Movies.getmOverview());
        FavModel.setPosterPath(Movies.getmPosterPath());
        FavModel.setVoteAverage(Movies.getmVoteAverage());
        FavModel.setCategory(Show);
        favoriteHelper.insertMovie(FavModel,MovieDetailActivity.this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        favoriteHelper.close();
    }
}
