package com.gregetdev.oris.dicodingsubmission;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.gregetdev.oris.dicodingsubmission.activity.Home.HomeActivity;
import com.gregetdev.oris.dicodingsubmission.api.Client;
import com.gregetdev.oris.dicodingsubmission.api.Service;
import com.gregetdev.oris.dicodingsubmission.model.Movie.MovieModel;
import com.gregetdev.oris.dicodingsubmission.model.Movie.ResponMovieModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.ALARM_SERVICE;

public class NewMovieNotification extends BroadcastReceiver {

    private int NEW_MOVIE_REMINDER_REQUEST_CODE = 101;

    @Override
    public void onReceive(Context context, Intent intent) {

        String content = context.getResources().getString(R.string.new_movie_notif);

        NewMovieNotification NewMovie = new NewMovieNotification();
        if(intent.getAction() == Intent.ACTION_BOOT_COMPLETED){
            NewMovie.SetReminder(context,NewMovieNotification.class,8,0);
        }
        NewMovie.getReleaseToday(context,content);
    }

    public void ShowNotification(Context context,Class cls,String title,String content){

        Intent intent = new Intent(context, cls);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.movie_icon)
                .setSound(alarmSound)
                .setContentIntent(pendingIntent);

        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(NEW_MOVIE_REMINDER_REQUEST_CODE,notification);
        }

    }

    private void getReleaseToday(Context context, String message) {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = dateFormat.format(date);

        final String MovieContent = message;
        final Context Moviecontext = context;

        Service apiService = Client.getClient().create(Service.class);
        Call<ResponMovieModel> call = apiService.ReleaseToday(BuildConfig.myTokenMovieDB, today, today);
        call.enqueue(new Callback<ResponMovieModel>() {
            @Override
            public void onResponse(Call<ResponMovieModel> call, Response<ResponMovieModel> response) {
                if (response.isSuccessful()) {
                    List<MovieModel> listMovies = response.body().getMovieResults();
                    for (int i = 0; i < listMovies.size(); i++) {
                        String movieTitle = listMovies.get(i).getmTitle();
                        ShowNotification(Moviecontext, HomeActivity.class, MovieContent,movieTitle);
                    }
                } else {
                    int statusCode = response.code();
                    Log.d("API",""+statusCode);
                }
            }

            @Override
            public void onFailure(Call<ResponMovieModel> call, Throwable t) {

            }


        });
    }

    public void SetReminder(Context context, Class cls, int hour, int minute){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        ComponentName receiver = new ComponentName(context,cls);

        PackageManager packageManager = context.getPackageManager();
        packageManager.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
        Intent intent = new Intent(context,cls);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                NEW_MOVIE_REMINDER_REQUEST_CODE,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }

    public void cancelReminder(Context context, Class cls) {
        ComponentName receiver = new ComponentName(context, cls);
        PackageManager packageManager = context.getPackageManager();

        packageManager.setComponentEnabledSetting(
                receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
        );

        Intent intent = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, NEW_MOVIE_REMINDER_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        pendingIntent.cancel();
    }
}
