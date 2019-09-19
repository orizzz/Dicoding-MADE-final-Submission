package com.gregetdev.oris.dicodingsubmission.api;

import com.gregetdev.oris.dicodingsubmission.model.Movie.ResponMovieModel;
import com.gregetdev.oris.dicodingsubmission.model.tv.ResponTvModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Service {
    @GET("discover/movie")
    Call<ResponMovieModel> DiscoveMovies(
            @Query("api_key") String apiKey);

    @GET("discover/tv")
    Call<ResponTvModel> DiscoverTv(
            @Query("api_key") String apiKey);

    @GET("search/movie")
    Call<ResponMovieModel> searchMovie(
            @Query("api_key") String apiKey,
            @Query("query") String query
    );

    @GET("search/tv")
    Call<ResponTvModel> searchTv(
            @Query("api_key") String apiKey,
            @Query("query") String query
    );

    @GET("discover/movie")
    Call<ResponMovieModel> ReleaseToday(
            @Query("api_key") String apiKey,
            @Query("primary_release_date.gte") String gteDate,
            @Query("primary_release_date.lte") String lteDate
    );
}

