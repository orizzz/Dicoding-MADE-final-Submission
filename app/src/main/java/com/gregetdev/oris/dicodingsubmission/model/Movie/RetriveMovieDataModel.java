package com.gregetdev.oris.dicodingsubmission.model.Movie;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.gregetdev.oris.dicodingsubmission.BuildConfig;
import com.gregetdev.oris.dicodingsubmission.api.Client;
import com.gregetdev.oris.dicodingsubmission.api.Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetriveMovieDataModel extends ViewModel {

    private MutableLiveData<List<MovieModel>> movieslist = new MutableLiveData<>();

    public void setMovieList(final Context context){
        try {
            Service apiService = Client.getClient().create(Service.class);
            Call<ResponMovieModel> call = apiService.DiscoveMovies(BuildConfig.myTokenMovieDB);
            call.enqueue(new Callback<ResponMovieModel>() {
                @Override
                public void onResponse(Call<ResponMovieModel> call, Response<ResponMovieModel> response) {
                    if (response.isSuccessful()) {
                        List<MovieModel> moviesResult = response.body().getMovieResults();
                        movieslist.postValue(moviesResult);
                    } else {
                        int statusCode = response.code();
                        Toast.makeText(context,"Failed to Fetch data, try checking your connection",Toast.LENGTH_SHORT).show();
                        Log.d("API",""+statusCode);
                    }
                }

                @Override
                public void onFailure(Call<ResponMovieModel> call, Throwable t) {
                    Toast.makeText(context,"Failed to Fetch data, try checking your connection",Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void SearchMovie(final Context context, String query){
        try {
            Service apiService = Client.getClient().create(Service.class);
            Call<ResponMovieModel> call = apiService.searchMovie(BuildConfig.myTokenMovieDB,query);
            call.enqueue(new Callback<ResponMovieModel>() {
                @Override
                public void onResponse(Call<ResponMovieModel> call, Response<ResponMovieModel> response) {
                    if (response.isSuccessful()) {
                        List<MovieModel> moviesResult = response.body().getMovieResults();
                        movieslist.postValue(moviesResult);
                    } else {
                        int statusCode = response.code();
                        Toast.makeText(context,"Failed to Fetch data, try checking your connection",Toast.LENGTH_SHORT).show();
                        Log.d("API",""+statusCode);
                    }
                }

                @Override
                public void onFailure(Call<ResponMovieModel> call, Throwable t) {
                    Toast.makeText(context,"Failed to Fetch data, try checking your connection",Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public LiveData<List<MovieModel>> getMovieslist() {
        return movieslist;
    }
}
