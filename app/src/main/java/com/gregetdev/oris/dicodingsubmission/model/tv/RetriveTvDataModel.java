package com.gregetdev.oris.dicodingsubmission.model.tv;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.gregetdev.oris.dicodingsubmission.BuildConfig;
import com.gregetdev.oris.dicodingsubmission.api.Client;
import com.gregetdev.oris.dicodingsubmission.api.Service;
import com.gregetdev.oris.dicodingsubmission.model.Movie.MovieModel;
import com.gregetdev.oris.dicodingsubmission.model.Movie.ResponMovieModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetriveTvDataModel extends ViewModel {

    private MutableLiveData<List<TvModel>> movieslist = new MutableLiveData<>();

    public void setMovieList(final Context context){
        try {
            Service apiService = Client.getClient().create(Service.class);
            Call<ResponTvModel> call = apiService.DiscoverTv(BuildConfig.myTokenMovieDB);
            call.enqueue(new Callback<ResponTvModel>() {
                @Override
                public void onResponse(Call<ResponTvModel> call, Response<ResponTvModel> response) {
                    if (response.isSuccessful()) {
                        List<TvModel> moviesResult = response.body().getTvResults();
                        movieslist.postValue(moviesResult);
                    } else {
                        int statusCode = response.code();
                        Toast.makeText(context,"Failed to Fetch data, try checking your connection",Toast.LENGTH_SHORT).show();
                        Log.d("API",""+statusCode);
                    }
                }

                @Override
                public void onFailure(Call<ResponTvModel> call, Throwable t) {
                    Toast.makeText(context,"Failed to Fetch data, try checking your connection",Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){

        }

    }

    public void SearchTv(final Context context, String query){
        try {
            Service apiService = Client.getClient().create(Service.class);
            Call<ResponTvModel> call = apiService.searchTv(BuildConfig.myTokenMovieDB,query);
            call.enqueue(new Callback<ResponTvModel>() {
                @Override
                public void onResponse(Call<ResponTvModel> call, Response<ResponTvModel> response) {
                    if (response.isSuccessful()) {
                        List<TvModel> moviesResult = response.body().getTvResults();
                        movieslist.postValue(moviesResult);
                    } else {
                        int statusCode = response.code();
                        Toast.makeText(context,"Failed to Fetch data, try checking your connection",Toast.LENGTH_SHORT).show();
                        Log.d("API",""+statusCode);
                    }
                }

                @Override
                public void onFailure(Call<ResponTvModel> call, Throwable t) {
                    Toast.makeText(context,"Failed to Fetch data, try checking your connection",Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public LiveData<List<TvModel>> getTvlist() {
        return movieslist;
    }

}
