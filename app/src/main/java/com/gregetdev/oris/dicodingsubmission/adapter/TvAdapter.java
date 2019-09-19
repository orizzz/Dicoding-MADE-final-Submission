package com.gregetdev.oris.dicodingsubmission.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gregetdev.oris.dicodingsubmission.R;
import com.gregetdev.oris.dicodingsubmission.activity.Detail.TvDetailActivity;
import com.gregetdev.oris.dicodingsubmission.model.tv.TvModel;

import java.util.ArrayList;
import java.util.List;

import static com.gregetdev.oris.dicodingsubmission.api.Client.IMAGE_URL;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.MovieViewHolder> {

    private Context context;
    private List<TvModel> movieList = new ArrayList<>();
    private String Show="";
    private String Intent_code = "MovieDetail";
    private String Intent_show = "Show";


    public void setData(List<TvModel> items, String Show, Context context) {
        movieList.clear();
        movieList.addAll(items);
        notifyDataSetChanged();
        this.context = context;
        this.Show = Show;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_item_film,viewGroup,false);
        return new TvAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder ViewHolder, int i) {
        ViewHolder.Title.setText(movieList.get(i).getmName());
        ViewHolder.Rating.setText(String.valueOf(movieList.get(i).getmVoteAverage()));
        Glide.with(context)
                .load(IMAGE_URL+movieList.get(i).getmPosterPath())
                .into(ViewHolder.Poster);
        final int position = ViewHolder.getAdapterPosition();

        ViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TvModel parceMovie = movieList.get(position);
                Intent intent = new Intent(context, TvDetailActivity.class);
                intent.putExtra(Intent_code,parceMovie);
                intent.putExtra(Intent_show,Show);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{

        TextView Title, Rating;
        ImageView Poster;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            Title = itemView.findViewById(R.id.view_judul_film);
            Rating = itemView.findViewById(R.id.view_rating_film);
            Poster = itemView.findViewById(R.id.view_image_film);

        }
    }

}
