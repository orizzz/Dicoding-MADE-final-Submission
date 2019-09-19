package com.gregetdev.oris.dicodingsubmission.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.gregetdev.oris.dicodingsubmission.R;
import com.gregetdev.oris.dicodingsubmission.db.FavoriteHelper;
import com.gregetdev.oris.dicodingsubmission.model.FavoriteModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.gregetdev.oris.dicodingsubmission.api.Client.IMAGE_URL;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private List<FavoriteModel> FavItems = new ArrayList<>();
    private final Context mContext;
    private FavoriteHelper favoriteHelper;
    private Cursor cursor;

    StackRemoteViewsFactory(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        favoriteHelper = FavoriteHelper.getInstance(mContext);
        favoriteHelper.open();
        FavItems.addAll(favoriteHelper.setAllMovies());
        FavItems.addAll(favoriteHelper.setAllTv());

    }

    @Override
    public void onDataSetChanged() {
        favoriteHelper = FavoriteHelper.getInstance(mContext);
        favoriteHelper.open();
        FavItems.addAll(favoriteHelper.setAllMovies());
        FavItems.addAll(favoriteHelper.setAllTv());
    }

    @Override
    public void onDestroy() {
        favoriteHelper.close();
    }

    @Override
    public int getCount() {
        return FavItems.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        FavoriteModel item = FavItems.get(position);
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.favorite_widget_item);
        Bitmap bitmap = null;
        try {
            //noinspection deprecation
            bitmap = Glide.with(mContext)
                    .asBitmap()
                    .load(IMAGE_URL + item.getPosterPath())
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        rv.setImageViewBitmap(R.id.widget_imageView, bitmap);

        Bundle extras = new Bundle();
        extras.putInt(FavoriteWidget.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.widget_imageView, fillInIntent);

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
