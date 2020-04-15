package com.example.photoeng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import static com.example.photoeng.DictionaryForLearningActivity.KEY_FOR_LEARNING_ARRAY;
import static com.example.photoeng.MainScreen.SPEECH_ARRAY_MESSAGE;

public class LearningActivity extends AppCompatActivity {

    private static ArrayList<String> learningWords = new ArrayList<>();
    private Button StartButton;
    private Button StopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
        StartButton = findViewById(R.id.start_learning_button);
        StopButton = findViewById(R.id.stop_learning_button);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Intent serviceIntent = new Intent(LearningActivity.this, HelloService.class);
        serviceIntent.putExtra(SPEECH_ARRAY_MESSAGE, learningWords);
        startService(serviceIntent);

        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    HelloService.say(learningWords);
            }
        });

        StopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelloService.stop();
                DictionaryForLearningActivity.getWordsToLearn().clear();
            }
        });

    }

    public static void setLearningWords(ArrayList<String> learningWords) {
        LearningActivity.learningWords = learningWords;
    }
}
