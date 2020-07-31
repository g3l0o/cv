package com.roger.cv.util;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class AnalyticsUtil {

    private static AnalyticsUtil instance;
    private FirebaseAnalytics firebaseAnalytics;

    private AnalyticsUtil(Context context){
        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    public static AnalyticsUtil getInstance(Context context){
        if(instance == null){
            instance = new AnalyticsUtil(context);
        }
        return instance;
    }

    public void logEventViewItem(String itemName){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, itemName);

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
    }

    public void logEventOpenApp(){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "open_app");

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle);
    }

}
