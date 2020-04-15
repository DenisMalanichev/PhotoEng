package com.example.photoeng;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.photoeng.adapters.Adapter;
import com.example.photoeng.data.DBHelper;

import java.util.ArrayList;


public class DictionaryActivity extends AppCompatActivity {


    private DBHelper dbhelper;
    private androidx.recyclerview.widget.RecyclerView DictionaryView;
    public static ArrayList<String> tempD;
    public static ArrayList<String> tempD2;
    private ProgressBar mBar;





    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        DictionaryView = findViewById(R.id.dictionary_view);
        mBar = findViewById(R.id.progress_circular);
        dbhelper = new DBHelper(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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