package com.example.photoeng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;

import com.example.photoeng.adapters.IntroAdapter;
import com.example.photoeng.data.DBHelper;

public class StartActivity extends AppCompatActivity {
    private static final int LOADING_TIME = 2500;
    private ViewPager mViewPager;
    private static final String INTRO_KEY = "show_intro";
    private SharedPreferences sPref;
    public static OfflineTranslateThread OTT = new OfflineTranslateThread();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mViewPager = findViewById(R.id.viewPager);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                sPref = getPreferences(MODE_PRIVATE);
                boolean intro = sPref.getBoolean(INTRO_KEY, true);
                OTT.start();
                if(intro) {
                    IntroAdapter introAdapter = new IntroAdapter(getSupportFragmentManager());
                    mViewPager.setAdapter(introAdapter);
                    SharedPreferences.Editor editor = sPref.edit();
                    editor.putBoolean(INTRO_KEY, false);
                    editor.commit();
                }else{
                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        }, LOADING_TIME);
    }

    public static OfflineTranslateThread getOTT() {
        return OTT;
    }
}
