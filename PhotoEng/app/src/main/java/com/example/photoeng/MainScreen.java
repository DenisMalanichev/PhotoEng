package com.example.photoeng;


import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Locale;
import java.util.Scanner;


public class MainScreen extends MainActivity {

    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    private Button Translate;
    private EditText TextReader;
    private TextView TranslatedWord;
    public ArrayList<String> linesArray;
    private Button SayButton;
    private TextToSpeech TTS;
    private Button MicrophoneButton;




    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        Translate = (Button) findViewById(R.id.research_button);
        TextReader = (EditText) findViewById(R.id.text_reader);
        TranslatedWord = (TextView) findViewById(R.id.translated_word);
        SayButton = (Button) findViewById(R.id.say);




        TTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result = TTS.setLanguage(Locale.UK);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS", "Language not supported");
                    }else{
                        SayButton.setEnabled(true);
                    }
                }else{
                    Log.e("TTS", "Initialization failed");
                }
            }
        });




       try {
           InputStream is = getAssets().open("en_rus.TXT");
           Scanner sc = new Scanner(is);
           while (sc.nextLine() != null) {
               linesArray.add(sc.nextLine());
           }
        }catch (IOException e){
            e.printStackTrace();
        }
        final ArrayList<String> ruList = new ArrayList<>();
        final ArrayList<String> engList = new ArrayList<>();
        for(int i =0; i<102762; i++){
            if(i%2 == 0){
                ruList.add(linesArray.get(i));

            }else{
                engList.add(linesArray.get(i));
            }
        }

        TranslatedWord.setText(linesArray.get(6241));

        TextReader.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


       Translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               for(int i =0; i<engList.size(); i++) {
                    if(TextReader.getText().equals(engList.get(i))) {
                        TranslatedWord.setText(ruList.get(i));
                        String engWord = engList.get(1);
                    }else{
                        TranslatedWord.setText("Not found");
                    }
                }
            }
        });
        SayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TTS.speak(TextReader.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);

            }
        });

        MicrophoneButton = (Button) findViewById(R.id.microphone_button);
        MicrophoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
    }


    @Override
    protected void onDestroy() {
        if(TTS != null) {
            TTS.stop();
            TTS.shutdown();
        }
        super.onDestroy();

    }

    public void speak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi");

        try{
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQUEST_CODE_SPEECH_INPUT:{
                if(resultCode == RESULT_OK && null!=data ){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                   TextReader.setText(result.get(0));
                }
                break;
            }
        }
    }

}
