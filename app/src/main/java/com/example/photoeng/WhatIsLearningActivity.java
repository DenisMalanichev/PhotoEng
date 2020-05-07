package com.example.photoeng;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class WhatIsLearningActivity extends AppCompatActivity {
    private TextView cross;
    private CheckBox check;
    private TextView whatIsText;
    private SharedPreferences sPref;
    private static final String INTRO_KEY = "show_help";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hat_is_learning);



        cross = findViewById(R.id.cross_textview);
        check = findViewById(R.id.ckeckbox);
        whatIsText = findViewById(R.id.what_is_text);
        String str = whatIsText.getText().toString();
        whatIsText.setText(str + getEmojiByUnicode(0x1F3A7) + getEmojiByUnicode(0x1F60E));
        sPref = getPreferences(MODE_PRIVATE);
        boolean help = sPref.getBoolean(INTRO_KEY, true);

        if(help == false){
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
            editor.putBoolean(INTRO_KEY, false);
            editor.commit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(check.isChecked()){
            sPref = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = sPref.edit();
            editor.putBoolean(INTRO_KEY, false);
            editor.commit();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(check.isChecked()){
            sPref = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = sPref.edit();
            editor.putBoolean(INTRO_KEY, false);
            editor.commit();
        }
    }
    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}
