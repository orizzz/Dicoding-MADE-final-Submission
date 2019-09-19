package com.gregetdev.oris.dicodingsubmission;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    LinearLayout languageSetting;
    Switch dailynotif,NewMovie;
    Toolbar toolbar;

    SharedPreferences settingPreferences;
    SharedPreferences.Editor prefsEditor;
    String APP_SHARED_PREFS = "MovieCatalogePref";
    String ReminderKey = "Reminder";
    String NewMovieKey = "NewMovie";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().setTitle("");

        languageSetting = findViewById(R.id.Language_setting);
        dailynotif = findViewById(R.id.daily_switch);
        NewMovie = findViewById(R.id.new_movie_switch);

        settingPreferences = getSharedPreferences(APP_SHARED_PREFS,Context.MODE_PRIVATE);
        dailynotif.setChecked(getState(ReminderKey));
        NewMovie.setChecked(getState(NewMovieKey));

        dailynotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveState(ReminderKey,isChecked);
                ReminderNotification reminder = new ReminderNotification();

                if (isChecked){
                    reminder.SetReminder(SettingActivity.this,ReminderNotification.class,7,0);
                }else {
                    reminder.cancelReminder(SettingActivity.this,ReminderNotification.class);
                }
            }
        });

        NewMovie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveState(NewMovieKey,isChecked);
                NewMovieNotification NewMovie = new NewMovieNotification();

                if (isChecked){
                    NewMovie.SetReminder(SettingActivity.this,NewMovieNotification.class,8,0);
                }else {
                    NewMovie.cancelReminder(SettingActivity.this,NewMovieNotification.class);
                }
            }
        });

        languageSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(mIntent);
            }
        });
    }

    public void saveState(String Key, Boolean stated){
        prefsEditor = settingPreferences.edit();
        prefsEditor.putBoolean(Key,stated);
        prefsEditor.commit();
    }

    public Boolean getState(String Key){
        return settingPreferences.getBoolean(Key,false);
    }
}
