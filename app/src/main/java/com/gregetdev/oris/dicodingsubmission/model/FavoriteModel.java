package com.gregetdev.oris.dicodingsubmission.model;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

public class FavoriteModel implements Parcelable {

    private int DBID;
    private int ID;
    private String Title;
    private String Overview;
    private double VoteAverage;
    private String PosterPath;
    private String Category;

    public FavoriteModel() {
    }

    public FavoriteModel(int DBID, int ID, String title, String overview, double voteAverage, String posterPath, String category) {
        this.DBID = DBID;
        this.ID = ID;
        Title = title;
        Overview = overview;
        VoteAverage = voteAverage;
        PosterPath = posterPath;
        Category = category;
    }


    public int getDBID() {
        return DBID;
    }

    public void setDBID(int DBID) {
        this.DBID = DBID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }

    public double getVoteAverage() {
        return VoteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        VoteAverage = voteAverage;
    }

    public String getPosterPath() {
        return PosterPath;
    }

    public void setPosterPath(String posterPath) {
        PosterPath = posterPath;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.DBID);
        dest.writeInt(this.ID);
        dest.writeString(this.Title);
        dest.writeString(this.Overview);
        dest.writeDouble(this.VoteAverage);
        dest.writeString(this.PosterPath);
        dest.writeString(this.Category);
    }

    public FavoriteModel(Parcel in) {
        this.DBID = in.readInt();
        this.ID = in.readInt();
        this.Title = in.readString();
        this.Overview = in.readString();
        this.VoteAverage = in.readDouble();
        this.PosterPath = in.readString();
        this.Category = in.readString();
    }

    public static final Creator<FavoriteModel> CREATOR = new Creator<FavoriteModel>() {
        @Override
        public FavoriteModel createFromParcel(Parcel source) {
            return new FavoriteModel(source);
        }

        @Override
        public FavoriteModel[] newArray(int size) {
            return new FavoriteModel[size];
        }
    };
}
