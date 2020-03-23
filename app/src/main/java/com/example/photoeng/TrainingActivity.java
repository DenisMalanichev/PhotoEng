package com.example.photoeng;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

public class TrainingActivity extends Activity {
    private DBHelper dbhelper;
    private EditText TextToCheck;
    private Button Check;
    private TextView  TextToTranslate;
    private  String[] wordsToTrain;
    private int[] wordsToTrainId;
    private Handler mHandler = new Handler();
    private int c = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        dbhelper = new DBHelper(this);
        TextToCheck = findViewById(R.id.training_edittext);
        Check = findViewById(R.id.check_button);
        TextToTranslate = findViewById(R.id.textview_word_to_translate);



        train(TranslationArray());



        int[] test = new int[5];
        TimeThread timeThread = new TimeThread(10);
        for(int i =0; i<5; i++){
            test[i] = i;
            TextToTranslate.setText(""+test[i]);
             new Thread(timeThread).start();
        }


    }


    private void train(ArrayList<String> temp){

        if(temp.size()>=5) {
            int count = temp.size();
            wordsToTrain = new String[5];
            wordsToTrainId =  arrayRandom(count);
            for (int i = 0; i < 5; i++) {
                wordsToTrain[i] = temp.get(wordsToTrainId[i]-1);
                Log.d("DEBUG: words to train", "" + wordsToTrain[i]);
            }
        }else{
            Log.d("DEBUG temp size", ""+temp.size());
            Toast.makeText(this,"Не достаточно слов", Toast.LENGTH_LONG).show();
        }


    }


    public static int[] arrayRandom(int c){

        int i, j, arraySize = 5;
        Random newRandom = new Random();
        int[] array1 = new int[arraySize];

        for (i = 0; i < array1.length; ) {
            int randomNumber = newRandom.nextInt(c) + 1;

            for (j = 0; j < i; j++) {
                if (array1[j] == randomNumber) {
                    break;
                }
            }
            if (j == i) {
                array1[i] = randomNumber;
                i++;
            }
        }
            return array1;
    }


    public ArrayList<String> ArrayHelper(){
        SQLiteDatabase database = dbhelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null,
                null, null, null, null);
        ArrayList<String> wordsArray = new ArrayList<>();

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            do {
                wordsArray.add(cursor.getString(nameIndex));
            } while (cursor.moveToNext());
        }
        dbhelper.close();
        cursor.close();
        return wordsArray;
    }
    private ArrayList<String> TranslationArray(){
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            for (int i = 0; i < ArrayHelper().size(); i++) {
                arrayList.add(TranslateYandex(ArrayHelper().get(i), "en-ru"));
            }}catch(Exception e){
            e.printStackTrace();
        }
        return arrayList;
    }

    public String TranslateYandex(String textToBeTranslated, String languagePair) {
        YandexTranslate translatorBackgroundTask = new YandexTranslate();
        return translatorBackgroundTask.doInBackground(textToBeTranslated, languagePair);
    }


    private void Asking(int queue){
            TextToTranslate.setText(wordsToTrain[queue]);
    }


    class TimeThread implements Runnable{
        int seconds;
        TimeThread(int seconds){
            this.seconds = seconds;
        }
        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(TrainingActivity.this, "Время пошло!", Toast.LENGTH_SHORT).show();
                }
            });
            for(int i =0; i <seconds; i++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(TrainingActivity.this, "Время вышло!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
