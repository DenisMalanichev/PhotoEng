package com.example.photoeng;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DictionaryActivity extends AppCompatActivity {

    private DBHelper db;
    private ListView DictionaryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        Cursor cursor;
        String[] words = getResources().getStringArray(R.array.words);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, contacts);
        DictionaryList = findViewById(R.id.dictionary_list_view);
        
    }

}
