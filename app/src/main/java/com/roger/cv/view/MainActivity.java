package com.roger.cv.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.roger.cv.R;
import com.roger.cv.util.AnalyticsUtil;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnalyticsUtil util = AnalyticsUtil.getInstance(this);
        util.logEventOpenApp();

        navController = Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, (DrawerLayout) null);
    }

    public void setToolbarTitle(String title){
        getSupportActionBar().setTitle(title);
    }
}
