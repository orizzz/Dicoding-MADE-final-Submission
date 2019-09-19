package com.gregetdev.oris.dicodingsubmission.activity.Detail;

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
import com.gregetdev.oris.dicodingsubmission.model.tv.TvModel;

import static com.gregetdev.oris.dicodingsubmission.api.Client.IMAGE_URL;

public class TvDetailActivity extends AppCompatActivity {

    private String Intent_code = "MovieDetail";
    private String Intent_show = "Show";

    private FloatingActionButton fab_fav;
    private FloatingActionButton fab_del;
    TextView title, vote,desc,date;
    ImageView img;

    private TvModel Tv;
    private FavoriteHelper favoriteHelper;
    String Show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);

        fab_fav = findViewById(R.id.FAB_add_favorite_tv);

        Show = getIntent().getStringExtra(Intent_show);
        Tv = getIntent().getParcelableExtra(Intent_code);

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
        title.setText(Tv.getmName());
        vote.setText(String.valueOf(Tv.getmVoteAverage()));
        desc.setText(Tv.getmOverview());
        date.setText(Tv.getmFirstAirDate());
        Glide.with(TvDetailActivity.this)
                .load(IMAGE_URL+Tv.getmPosterPath())
                .into(img);
    }

    private void AddFavorite(View view) {

        FavoriteModel FavModel = new FavoriteModel();
        FavModel.setID(Tv.getmId());
        FavModel.setTitle(Tv.getmName());
        FavModel.setOverview(Tv.getmOverview());
        FavModel.setPosterPath(Tv.getmPosterPath());
        FavModel.setVoteAverage(Tv.getmVoteAverage());
        FavModel.setCategory(Show);
        favoriteHelper.insertMovie(FavModel,TvDetailActivity.this);

    }

}
