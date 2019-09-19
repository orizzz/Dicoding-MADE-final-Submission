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

import com.gregetdev.oris.dicodingsubmission.activity.Home.HomeActivity;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class ReminderNotification extends BroadcastReceiver {

    private int DAILY_REMINDER_REQUEST_CODE = 100;

    @Override
    public void onReceive(Context context, Intent intent) {
        String title = context.getResources().getString(R.string.reminder_title);
        String content = context.getResources().getString(R.string.reminder_content);
        ReminderNotification reminder = new ReminderNotification();
        if(intent.getAction() == Intent.ACTION_BOOT_COMPLETED){
            reminder.SetReminder(context,ReminderNotification.class,7,0);
        }
        reminder.ShowNotification(context, HomeActivity.class, title,content);
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
            notificationManager.notify(DAILY_REMINDER_REQUEST_CODE,notification);
        }

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
                DAILY_REMINDER_REQUEST_CODE,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }

    public void cancelReminder(Context context, Class cls) {
        ComponentName receiver =  new ComponentName(context,cls);
        PackageManager packageManager = context.getPackageManager();

        packageManager.setComponentEnabledSetting(
                receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
        );

        Intent intent = new Intent(context,cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,DAILY_REMINDER_REQUEST_CODE,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        pendingIntent.cancel();

    }

}
