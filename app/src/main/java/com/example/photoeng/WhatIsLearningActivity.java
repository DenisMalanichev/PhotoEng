package com.example.photoeng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class WhatIsLearningActivity extends AppCompatActivity {
    private TextView cross;
    private CheckBox check;
    private SharedPreferences sPref;
    private static final String INTRO_KEY = "show_help";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hat_is_learning);

        cross = findViewById(R.id.cross_textview);
        check = findViewById(R.id.ckeckbox);

        sPref = getPreferences(MODE_PRIVATE);
        boolean help = sPref.getBoolean(INTRO_KEY, true);

        if(help){
            Intent intent = new Intent(WhatIsLearningActivity.this, LearningActivity.class);
            startActivity(intent);
        }

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WhatIsLearningActivity.this, LearningActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(check.isChecked()){
            sPref = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = sPref.edit();
            editor.putBoolean(INTRO_KEY, true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(check.isChecked()){
            sPref = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = sPref.edit();
            editor.putBoolean(INTRO_KEY, true);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(check.isChecked()){
            sPref = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = sPref.edit();
            editor.putBoolean(INTRO_KEY, true);
        }
    }
}
