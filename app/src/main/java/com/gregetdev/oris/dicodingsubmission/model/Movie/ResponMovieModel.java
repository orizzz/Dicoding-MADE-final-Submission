package com.gregetdev.oris.dicodingsubmission.model.Movie;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponMovieModel {

    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private ArrayList<MovieModel> MovieResults;
    @SerializedName("total_results")
    private int total_results;
    @SerializedName("total_pages")
    private int total_pages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<MovieModel> getMovieResults() {
        return MovieResults;
    }
    public void setMovieResults(ArrayList<MovieModel> movieResults) {
        MovieResults = movieResults;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }
}
