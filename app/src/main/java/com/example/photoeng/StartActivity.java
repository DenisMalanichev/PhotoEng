package com.example.photoeng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {
    private static final int LOADING_TIME = 15000;
    private DBHelper dbhelper;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dbhelper = new DBHelper(context);
                DictionaryActivity.setTemp(CursorHelper());
                DictionaryActivity.setTemp2(TranslationArray());
                DictionaryForLearningActivity.setTemp(CursorHelper());
                DictionaryForLearningActivity.setTemp2(TranslationArray());
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, LOADING_TIME);*/
       OnlineTranslateTread OnTT = new OnlineTranslateTread(this);
       OnlineTranslationForLearning OnTTFl = new OnlineTranslationForLearning(this);
       OnTT.start();
       OnTTFl.start();
       do{

       }while(OnTT.isAlive() || OnTTFl.isAlive());
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        startActivity(intent);
    }


}
