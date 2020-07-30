package com.roger.cv.util;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.preference.PreferenceManager;

public class SharedPreferenceHelper {

    private static final String PREF_JOBS_TIME = "PrefJobsTime";
    private static final String PREF_INFORMATION_TIME = "PrefInformationTime";

    private static SharedPreferenceHelper instance;
    private SharedPreferences prefs;

    private SharedPreferenceHelper(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPreferenceHelper getInstance(Context context){
        if(instance == null){
            instance = new SharedPreferenceHelper(context);
        }
        return instance;
    }

    public void saveJobsUpdateTime(long time){
        prefs.edit().putLong(PREF_JOBS_TIME, time).apply();
    }

    public long getJobsUpdateTime(){
        return prefs.getLong(PREF_JOBS_TIME, 0L);
    }

    public void saveInformationUpdateTime(long time){
        prefs.edit().putLong(PREF_INFORMATION_TIME, time).apply();
    }

    public long getInformationUpdateTime(){
        return prefs.getLong(PREF_INFORMATION_TIME, 0L);
    }

}
