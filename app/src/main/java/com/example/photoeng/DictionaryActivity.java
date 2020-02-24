package com.example.photoeng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DictionaryActivity extends AppCompatActivity {

    private String s1[], s2[];
    Context mContext;
    private DBHelper db;
    private androidx.recyclerview.widget.RecyclerView DictionaryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        Cursor cursor;
        String[] words = getResources().getStringArray(R.array.words);

        DictionaryView = findViewById(R.id.dictionary_view);

        s1 = getResources().getStringArray(R.array.words);
        s2 = getResources().getStringArray(R.array.translation);
        Adapter adapter = new Adapter(this, s1, s2);
        DictionaryView.setAdapter(adapter);
        DictionaryView.setLayoutManager(new LinearLayoutManager(this));

    }

}
