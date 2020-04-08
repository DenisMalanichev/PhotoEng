package com.example.photoeng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class LoadingActivity extends AppCompatActivity {

    private OnlineTranslateTread OnTT = new OnlineTranslateTread(this);
    private ProgressBar mBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        mBar = findViewById(R.id.progress_circular);

        OnTT.start();
          do {
        }while(OnTT.isAlive());


        Intent intent = new Intent(LoadingActivity.this, DictionaryActivity.class);
        startActivity(intent);
    }

}
