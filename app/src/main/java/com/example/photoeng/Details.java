package com.example.photoeng;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.photoeng.data.DBHelper;

import java.util.Locale;

public class Details extends MainScreen {
    private TextView DetailsText;
    private ImageButton DeleteButton;
    private DBHelper mDBHelper;
    private TextView translatedText;
    private ImageButton sayButton;
    private TextToSpeech TTS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        DetailsText = findViewById(R.id.details_text);
        DeleteButton = findViewById(R.id.delete_button);
        mDBHelper = new DBHelper(this);
        translatedText = findViewById(R.id.details_text_translated);
        sayButton = findViewById(R.id.speaker_button);

        Intent intent = getIntent();
        final String title = intent.getStringExtra("title");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DetailsText.setText(title);
        translatedText.setText(intent.getStringExtra("translation"));


        TTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = TTS.setLanguage(Locale.UK);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        sayButton.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        sayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String words = DetailsText.getText().toString();
                TTS.speak(words, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i =0; i<DictionaryActivity.getTemp().size(); i++){
                    if(DictionaryActivity.getTemp().get(i).equals(title)){
                        DictionaryActivity.getTemp().remove(i);
                        break;
                    }
                }

                SQLiteDatabase database = mDBHelper.getWritableDatabase();
                int deletedItem = database.delete(mDBHelper.TABLE_CONTACTS, mDBHelper.KEY_WORDS+ " = ?", new  String[]{title});
                Toast.makeText(Details.this, "deleted", Toast.LENGTH_SHORT);
                Log.d("DEBAG: deleted item id", ""+deletedItem);
                mDBHelper.close();
                Intent i = new Intent(Details.this, DictionaryActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onDestroy() {
        if (TTS != null) {
            TTS.stop();
            TTS.shutdown();
        }
        super.onDestroy();

    }

}

