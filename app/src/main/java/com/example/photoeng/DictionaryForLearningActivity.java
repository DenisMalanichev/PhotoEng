package com.example.photoeng;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class DictionaryForLearningActivity extends AppCompatActivity {
    private DBHelper dbhelper;
    private androidx.recyclerview.widget.RecyclerView DictionaryView;
    public static ArrayList<String> wordsEngArray;
    public static ArrayList<String> wordsRuArray;
    public static ArrayList<String> wordsToLearn = new ArrayList<>();
    public static final String KEY_FOR_LEARNING_ARRAY = "extra_array_of_learning_words";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_for_learning);
        DictionaryView = findViewById(R.id.dictionary_for_learning_view);
        dbhelper = new DBHelper(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        OnlineTranslationForLearning OnTTFL = new OnlineTranslationForLearning(this);
        OnTTFL.start();

        do{
        }while(OnTTFL.isAlive());

        AdapterForLearning adapter = new AdapterForLearning(this, wordsEngArray, wordsRuArray);

        DictionaryView.setLayoutManager(new LinearLayoutManager(this));
        DictionaryView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ready_button:
               if(wordsToLearn.size() != 0) {
                   LearningActivity.setLearningWords(wordsToLearn);
                   Intent intent = new Intent(DictionaryForLearningActivity.this, LearningActivity.class);
                   startActivity(intent);
               }else{
                   Toast.makeText(this, "Выбери слова!", Toast.LENGTH_LONG).show();
               }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static ArrayList<String> getWordsEngArray() {
        return wordsEngArray;
    }

    public static ArrayList<String> getWordsRuArray() {
        return wordsRuArray;
    }

    public static void setTemp(ArrayList<String> tempD) {
        DictionaryForLearningActivity.wordsEngArray = tempD;
    }

    public static void setTemp2(ArrayList<String> tempD2) {
        DictionaryForLearningActivity.wordsRuArray = tempD2;
    }

    public static ArrayList<String> getWordsToLearn() {
        return wordsToLearn;
    }
}
