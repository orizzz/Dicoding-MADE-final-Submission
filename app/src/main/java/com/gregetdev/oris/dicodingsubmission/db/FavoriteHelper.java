package com.gregetdev.oris.dicodingsubmission.db;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import com.gregetdev.oris.dicodingsubmission.R;
import com.gregetdev.oris.dicodingsubmission.model.FavoriteModel;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static com.gregetdev.oris.dicodingsubmission.db.DatabaseContract.MovieColumns.*;
import static com.gregetdev.oris.dicodingsubmission.db.DatabaseContract.TABLE_MOVIE;

public class FavoriteHelper{

    private static final String DATABASE_MOVIE = TABLE_MOVIE;
    private static DatabaseHelper dataBaseHelper;
    private static FavoriteHelper INSTANCE;
    private static SQLiteDatabase database;

    private FavoriteHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public static FavoriteHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }
    public void close() {
        dataBaseHelper.close();
        if (database.isOpen())
            database.close();
    }

    public ArrayList<FavoriteModel> setAllMovies() {
        ArrayList<FavoriteModel> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_MOVIE, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        FavoriteModel FavModel;
        if (cursor.getCount()>0){
            do {
                String category = cursor.getString(cursor.getColumnIndexOrThrow(Category));
                Log.d("Data","category : "+category);
                if (category.equals("Movies")){
                    FavModel = new FavoriteModel();
                    FavModel.setID(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                    FavModel.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(Title)));
                    FavModel.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(Overview)));
                    FavModel.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(PosterPath)));
                    FavModel.setVoteAverage(cursor.getDouble(cursor.getColumnIndexOrThrow(VoteAverage)));
                    FavModel.setCategory(cursor.getString(cursor.getColumnIndexOrThrow(Category)));
                    arrayList.add(FavModel);
                    cursor.moveToNext();
                } else {
                    cursor.moveToNext();
                }
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }


    public ArrayList<FavoriteModel> setAllTv() {
        ArrayList<FavoriteModel> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_MOVIE, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        FavoriteModel FavModel;
        if (cursor.getCount()>0){
            do {
                String category = cursor.getString(cursor.getColumnIndexOrThrow(Category));
                Log.d("Data","category : "+category);
                if (category.equals("Tv")){
                    FavModel = new FavoriteModel();
                    FavModel.setID(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                    FavModel.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(Title)));
                    FavModel.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(Overview)));
                    FavModel.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(PosterPath)));
                    FavModel.setVoteAverage(cursor.getDouble(cursor.getColumnIndexOrThrow(VoteAverage)));
                    FavModel.setCategory(cursor.getString(cursor.getColumnIndexOrThrow(Category)));
                    arrayList.add(FavModel);
                    cursor.moveToNext();
                } else {
                    cursor.moveToNext();
                }
            }while (!cursor.isAfterLast());
        }
        cursor.close();
    return arrayList;
    }

    public void insertMovie(FavoriteModel FavModel,Context context){
        Cursor cursor = database.query(DATABASE_MOVIE, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            do {
                int Exist_ID = cursor.getInt(cursor.getColumnIndexOrThrow(ID));
                if (FavModel.getID() == Exist_ID) {
                    String text = context.getResources().getString(R.string.already_favorite);
                    Toast.makeText(context, text, Snackbar.LENGTH_LONG).show();
                    return;
                } else {
                    cursor.moveToNext();
                }
            } while (!cursor.isAfterLast());
        }
        ContentValues args = new ContentValues();
        args.put(ID,FavModel.getID());
        args.put(Title,FavModel.getTitle());
        args.put(Overview,FavModel.getOverview());
        args.put(PosterPath,FavModel.getPosterPath());
        args.put(VoteAverage,FavModel.getVoteAverage());
        args.put(Category,FavModel.getCategory());

        database.insert(DATABASE_MOVIE,null,args);
        String text = context.getResources().getString(R.string.added_to_favorite);
        Toast.makeText(context, text, Snackbar.LENGTH_LONG).show();
    }

    public void deleteMovie(int id,Context context) {
        database.delete(TABLE_MOVIE, ID + " = '" + id +"'", null);
        String text = context.getResources().getString(R.string.deltedfavorite);
        Toast.makeText(context, text, Snackbar.LENGTH_LONG).show();
    }
}
