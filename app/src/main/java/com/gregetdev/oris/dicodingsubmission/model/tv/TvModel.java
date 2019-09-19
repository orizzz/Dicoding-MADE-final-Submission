package com.gregetdev.oris.dicodingsubmission.model.tv;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TvModel implements Parcelable {

    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @SerializedName("first_air_date")
    private String mFirstAirDate;
    @SerializedName("genre_ids")
    private List<Long> mGenreIds;
    @SerializedName("id")
    private Integer mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("origin_country")
    private List<String> mOriginCountry;
    @SerializedName("original_language")
    private String mOriginalLanguage;
    @SerializedName("original_name")
    private String mOriginalName;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("popularity")
    private Double mPopularity;
    @SerializedName("poster_path")
    private String mPosterPath;
    @SerializedName("vote_average")
    private Double mVoteAverage;
    @SerializedName("vote_count")
    private Long mVoteCount;

    public TvModel() {
    }

    public TvModel(String mBackdropPath, String mFirstAirDate, List<Long> mGenreIds, Integer mId, String mName, List<String> mOriginCountry, String mOriginalLanguage, String mOriginalName, String mOverview, Double mPopularity, String mPosterPath, Double mVoteAverage, Long mVoteCount) {
        this.mBackdropPath = mBackdropPath;
        this.mFirstAirDate = mFirstAirDate;
        this.mGenreIds = mGenreIds;
        this.mId = mId;
        this.mName = mName;
        this.mOriginCountry = mOriginCountry;
        this.mOriginalLanguage = mOriginalLanguage;
        this.mOriginalName = mOriginalName;
        this.mOverview = mOverview;
        this.mPopularity = mPopularity;
        this.mPosterPath = mPosterPath;
        this.mVoteAverage = mVoteAverage;
        this.mVoteCount = mVoteCount;
    }

    public String getmBackdropPath() {
        return mBackdropPath;
    }

    public void setmBackdropPath(String mBackdropPath) {
        this.mBackdropPath = mBackdropPath;
    }

    public String getmFirstAirDate() {
        return mFirstAirDate;
    }

    public void setmFirstAirDate(String mFirstAirDate) {
        this.mFirstAirDate = mFirstAirDate;
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

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public List<String> getmOriginCountry() {
        return mOriginCountry;
    }

    public void setmOriginCountry(List<String> mOriginCountry) {
        this.mOriginCountry = mOriginCountry;
    }

    public String getmOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setmOriginalLanguage(String mOriginalLanguage) {
        this.mOriginalLanguage = mOriginalLanguage;
    }

    public String getmOriginalName() {
        return mOriginalName;
    }

    public void setmOriginalName(String mOriginalName) {
        this.mOriginalName = mOriginalName;
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
        dest.writeString(this.mBackdropPath);
        dest.writeString(this.mFirstAirDate);
        dest.writeList(this.mGenreIds);
        dest.writeValue(this.mId);
        dest.writeString(this.mName);
        dest.writeStringList(this.mOriginCountry);
        dest.writeString(this.mOriginalLanguage);
        dest.writeString(this.mOriginalName);
        dest.writeString(this.mOverview);
        dest.writeValue(this.mPopularity);
        dest.writeString(this.mPosterPath);
        dest.writeValue(this.mVoteAverage);
        dest.writeValue(this.mVoteCount);
    }

    protected TvModel(Parcel in) {
        this.mBackdropPath = in.readString();
        this.mFirstAirDate = in.readString();
        this.mGenreIds = new ArrayList<Long>();
        in.readList(this.mGenreIds, Long.class.getClassLoader());
        this.mId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mName = in.readString();
        this.mOriginCountry = in.createStringArrayList();
        this.mOriginalLanguage = in.readString();
        this.mOriginalName = in.readString();
        this.mOverview = in.readString();
        this.mPopularity = (Double) in.readValue(Double.class.getClassLoader());
        this.mPosterPath = in.readString();
        this.mVoteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.mVoteCount = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<TvModel> CREATOR = new Parcelable.Creator<TvModel>() {
        @Override
        public TvModel createFromParcel(Parcel source) {
            return new TvModel(source);
        }

        @Override
        public TvModel[] newArray(int size) {
            return new TvModel[size];
        }
    };
}
