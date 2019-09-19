package com.gregetdev.oris.dicodingsubmission.activity.Detail;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gregetdev.oris.dicodingsubmission.R;
import com.gregetdev.oris.dicodingsubmission.db.FavoriteHelper;
import com.gregetdev.oris.dicodingsubmission.model.FavoriteModel;

import static com.gregetdev.oris.dicodingsubmission.api.Client.IMAGE_URL;

public class FavMovieDetailActivity extends AppCompatActivity {

    private String Intent_code = "MovieDetail";
    private String Intent_show = "Show";
    private int position;

    private FloatingActionButton fab_del;
    TextView title, vote,desc,date;
    ImageView img;

    private FavoriteModel Movies;
    private FavoriteHelper favoriteHelper;
    String Show;

    public static final int RESULT_DELETE = 301;
    public static final String EXTRA_POSITION = "extra_position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_movie_detail);

        fab_del = findViewById(R.id.FAB_del_favorite_movie);

        Show = getIntent().getStringExtra(Intent_show);
        Movies = getIntent().getParcelableExtra(Intent_code);
        position = getIntent().getIntExtra(EXTRA_POSITION, 0);

        title = findViewById(R.id.detail_judul_film);
        vote = findViewById(R.id.detail_vote_film);
        desc = findViewById(R.id.detail_desc_film);
        img = findViewById(R.id.detail_image_film);
        date = findViewById(R.id.detail_date_film);

        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
        favoriteHelper.open();

        ShowData();

        fab_del.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DelFavorite(view);
            }
        });
    }

    private void DelFavorite(View view) {
        favoriteHelper.deleteMovie(Movies.getID(),FavMovieDetailActivity.this);
        Intent intent = new Intent();
        intent.putExtra(EXTRA_POSITION, position);
        setResult(RESULT_DELETE, intent);
        finish();
    }

    private void ShowData() {
        title.setText(Movies.getTitle());
        vote.setText(String.valueOf(Movies.getVoteAverage()));
        desc.setText(Movies.getOverview());
        Glide.with(FavMovieDetailActivity.this)
                .load(IMAGE_URL+Movies.getPosterPath())
                .into(img);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        favoriteHelper.close();
    }
}
