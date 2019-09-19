package com.gregetdev.oris.dicodingsubmission.db;

import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DatabaseContract  {

    static String TABLE_MOVIE = "Movies";


    static final class MovieColumns implements BaseColumns {
        static String ID = "mID";
        static String Title = "mTitle";
        static String Overview = "mOverview";
        static String VoteAverage = "mVoteAverage";
        static String PosterPath = "mPosterPath";
        static String Category = "mCategory";

    }

}
