package com.gregetdev.oris.dicodingsubmission.model.Movie;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieModel implements Parcelable {

    @SerializedName("adult")
    private Boolean mAdult;
    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @SerializedName("genre_ids")
    private List<Long> mGenreIds;
    @SerializedName("id")
    private Integer mId;
    @SerializedName("original_language")
    private String mOriginalLanguage;
    @SerializedName("original_title")
    private String mOriginalTitle;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("popularity")
    private Double mPopularity;
    @SerializedName("poster_path")
    private String mPosterPath;
    @SerializedName("release_date")
    private String mReleaseDate;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("video")
    private Boolean mVideo;
    @SerializedName("vote_average")
    private Double mVoteAverage;
    @SerializedName("vote_count")
    private Long mVoteCount;

    public MovieModel(Boolean mAdult, String mBackdropPath, List<Long> mGenreIds, Integer mId, String mOriginalLanguage, String mOriginalTitle, String mOverview, Double mPopularity, String mPosterPath, String mReleaseDate, String mTitle, Boolean mVideo, Double mVoteAverage, Long mVoteCount) {
        this.mAdult = mAdult;
        this.mBackdropPath = mBackdropPath;
        this.mGenreIds = mGenreIds;
        this.mId = mId;
        this.mOriginalLanguage = mOriginalLanguage;
        this.mOriginalTitle = mOriginalTitle;
        this.mOverview = mOverview;
        this.mPopularity = mPopularity;
        this.mPosterPath = mPosterPath;
        this.mReleaseDate = mReleaseDate;
        this.mTitle = mTitle;
        this.mVideo = mVideo;
        this.mVoteAverage = mVoteAverage;
        this.mVoteCount = mVoteCount;
    }

    public MovieModel() {
    }

    public Boolean getmAdult() {
        return mAdult;
    }

    public void setmAdult(Boolean mAdult) {
        this.mAdult = mAdult;
    }

    public String getmBackdropPath() {
        return mBackdropPath;
    }

    public void setmBackdropPath(String mBackdropPath) {
        this.mBackdropPath = mBackdropPath;
    }

    public List<Long> getmGenreIds() {
        return mGenreIds;
    }

    public void setmGenreIds(List<Long> mGenreIds) {
        this.mGenreIds = mGenreIds;
    }

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public String getmOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setmOriginalLanguage(String mOriginalLanguage) {
        this.mOriginalLanguage = mOriginalLanguage;
    }

    public String getmOriginalTitle() {
        return mOriginalTitle;
    }

    public void setmOriginalTitle(String mOriginalTitle) {
        this.mOriginalTitle = mOriginalTitle;
    }

    public String getmOverview() {
        return mOverview;
    }

    public void setmOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public Double getmPopularity() {
        return mPopularity;
    }

    public void setmPopularity(Double mPopularity) {
        this.mPopularity = mPopularity;
    }

    public String getmPosterPath() {
        return mPosterPath;
    }

    public void setmPosterPath(String mPosterPath) {
        this.mPosterPath = mPosterPath;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Boolean getmVideo() {
        return mVideo;
    }

    public void setmVideo(Boolean mVideo) {
        this.mVideo = mVideo;
    }

    public Double getmVoteAverage() {
        return mVoteAverage;
    }

    public void setmVoteAverage(Double mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }

    public Long getmVoteCount() {
        return mVoteCount;
    }

    public void setmVoteCount(Long mVoteCount) {
        this.mVoteCount = mVoteCount;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mAdult);
        dest.writeString(this.mBackdropPath);
        dest.writeList(this.mGenreIds);
        dest.writeValue(this.mId);
        dest.writeString(this.mOriginalLanguage);
        dest.writeString(this.mOriginalTitle);
        dest.writeString(this.mOverview);
        dest.writeValue(this.mPopularity);
        dest.writeString(this.mPosterPath);
        dest.writeString(this.mReleaseDate);
        dest.writeString(this.mTitle);
        dest.writeValue(this.mVideo);
        dest.writeValue(this.mVoteAverage);
        dest.writeValue(this.mVoteCount);
    }

    protected MovieModel(Parcel in) {
        this.mAdult = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mBackdropPath = in.readString();
        this.mGenreIds = new ArrayList<Long>();
        in.readList(this.mGenreIds, Long.class.getClassLoader());
        this.mId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mOriginalLanguage = in.readString();
        this.mOriginalTitle = in.readString();
        this.mOverview = in.readString();
        this.mPopularity = (Double) in.readValue(Double.class.getClassLoader());
        this.mPosterPath = in.readString();
        this.mReleaseDate = in.readString();
        this.mTitle = in.readString();
        this.mVideo = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mVoteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.mVoteCount = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel source) {
            return new MovieModel(source);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };
}
