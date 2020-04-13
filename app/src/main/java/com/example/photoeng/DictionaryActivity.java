package com.example.photoeng;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.photoeng.MainScreen.EXTRA_KEY_DICTIONARY_ACTIVITY;


public class DictionaryActivity extends AppCompatActivity {


    private DBHelper dbhelper;
    private androidx.recyclerview.widget.RecyclerView DictionaryView;
    public static ArrayList<String> tempD;
    public static ArrayList<String> tempD2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        DictionaryView = findViewById(R.id.dictionary_view);
        dbhelper = new DBHelper(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        OnlineTranslateTread OnTT = new OnlineTranslateTread(this);
        OnTT.start();
        do{
        }while (OnTT.isAlive());

        Adapter adapter = new Adapter(this, tempD, tempD2);

        Intent intent = new Intent(DictionaryActivity.this, TrainingActivity.class);
        intent.putExtra("extraWords", tempD);

        DictionaryView.setLayoutManager(new LinearLayoutManager(this));
        DictionaryView.setAdapter(adapter);
    }




    public static ArrayList<String> getTemp() {
        return tempD;
    }

    public static ArrayList<String> getTemp2() {
        return tempD2;
    }

    public static void setTemp(ArrayList<String> temp) {
        DictionaryActivity.tempD = temp;
    }

    public static void setTemp2(ArrayList<String> temp2) {
        DictionaryActivity.tempD2 = temp2;
    }


}