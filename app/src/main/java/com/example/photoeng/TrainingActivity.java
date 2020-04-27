package com.example.photoeng;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.photoeng.data.DBHelper;

import java.util.ArrayList;
import java.util.Random;

public class TrainingActivity extends Activity {

    private DBHelper dbhelper;
    private EditText TextToCheck;
    private Button Check;
    private TextView  TextToTranslate;
    private  String[] wordsToTrain;
    private int[] wordsToTrainId;
    private ImageView timerImageView;
    private TextView timerView;
    private CountDownTimer mTimer;
    private int currentId = 0;
    public static int trueAnswers = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        dbhelper = new DBHelper(this);
        TextToCheck = findViewById(R.id.training_edittext);
        Check = findViewById(R.id.check_button);
        TextToTranslate = findViewById(R.id.textview_word_to_translate);
        timerView = findViewById(R.id.timer_view);
        timerImageView = findViewById(R.id.timer_image_view);

        setWordsToTrain(ArrayHelper());
        //устанавливаем нулевое слово
        TextToTranslate.setText(wordsToTrain[0]);
        Log.d("DEBUG ans is", ""+TranslationArray().get(0));
        //вызываем метод для показа нового слова
        train(currentId);
    }

   /* private void showNewWord(int id) {
        TextToTranslate.setText(wordsToTrain[id]);
        mTimer = new CountDownTimer(15000, 1000) {

            //Здесь обновляем текст счетчика обратного отсчета с каждой секундой
            public void onTick(long millisUntilFinished) {
                timerView.setText(""+millisUntilFinished / 1000);
                if(millisUntilFinished < 6000){
                    timerView.setTextColor(TrainingActivity.this.getResources().getColor(R.color.red));
                    timerImageView.setImageResource(R.drawable.ic_timer_red);
                }
            }
            //Задаем действия после завершения отсчета
            public void onFinish() {
                 isAnswerTrue();
                 if(currentId < 4){
                    timerView.setTextColor(TrainingActivity.this.getResources().getColor(R.color.white));
                    timerImageView.setImageResource(R.drawable.ic_timer);
                }
            }
        }.start();

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








    private void isAnswerTrue(){
        Log.d("DEBUG ", ""+currentId);
        if(currentId < 4) {
            Log.d("DEBUG ans is", ""+wordsToTrain[wordsToTrainId[currentId]]);
            if(TextToCheck.getText().toString().trim().toLowerCase().equals(ArrayHelper().get(wordsToTrainId[currentId]))){
                trueAnswers++;
                Log.d("DEBUG ans", "true");
            }else{
                Log.d("DEBUG ans", "false");
            }
            currentId++;
            TextToCheck.setText("");
            showNewWord(currentId);
        }else if(currentId == 4){
                Log.d("DEBUG ans is", ""+ArrayHelper().get(wordsToTrainId[currentId]));
                if(TextToCheck.getText().toString().trim().toLowerCase().equals(ArrayHelper().get(wordsToTrainId[currentId]))){
                    trueAnswers++;
                    Log.d("DEBUG ans", "true");
                }else{
                    Log.d("DEBUG ans", "false");
                }
            Intent intent = new Intent(TrainingActivity.this, TrainingResult.class);
            intent.putExtra("ExtraTrueAnswers", trueAnswers);
            startActivity(intent);
        }
        Log.d("DEBUG ", ""+currentId);
    }*/

   public void train(int id){
       //set 1 word
       TextToTranslate.setText(TranslationArray().get(currentId));
       //start timer
       mTimer = new CountDownTimer(15000, 1000) {
           //update timer
           public void onTick(long millisUntilFinished) {
               timerView.setText(""+millisUntilFinished / 1000);
               if(millisUntilFinished < 6000){
                   timerView.setTextColor(TrainingActivity.this.getResources().getColor(R.color.red));
                   timerImageView.setImageResource(R.drawable.ic_timer_red);
               }
           }
          //task after time out
           public void onFinish() {
               //check word
               Log.d("DEBUG shicking word", "finish method");
               isAnswerTrue();
               /*if(currentId < 4){
                   timerView.setTextColor(TrainingActivity.this.getResources().getColor(R.color.white));
                   timerImageView.setImageResource(R.drawable.ic_timer);
               }*/
           }
       }.start();
   }

    public ArrayList<String> ArrayHelper(){
        SQLiteDatabase database = dbhelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null,
                null, null, null, null);
        ArrayList<String> wordsArray = new ArrayList<>();

        if (cursor.moveToFirst()) {

            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_WORDS);
            do {
                wordsArray.add(cursor.getString(nameIndex));
            } while (cursor.moveToNext());
        }
        dbhelper.close();
        cursor.close();
        return wordsArray;
    }


    public void isAnswerTrue(){
        Log.d("DEBUG shicking word", "isAnswerTrue method");
       if(TextToCheck.getText().toString().trim().toLowerCase().equals(wordsToTrain[currentId].trim().toLowerCase())){
           trueAnswers++;
           TextToCheck.setText("");
           Log.d("DEBUG shicking word", "if");
           if (currentId < 4){
               currentId++;
               train(currentId);
           }else{
               Intent intent = new Intent(TrainingActivity.this, TrainingResult.class);
               startActivity(intent);
           }
       }else{
           Log.d("DEBUG shicking word", "else");
           TextToCheck.setText("");
           if (currentId < 4){
               currentId++;
               train(currentId);
           }else{
               Intent intent = new Intent(TrainingActivity.this, TrainingResult.class);
               startActivity(intent);
           }
       }
       Log.d("DEBUG", ""+currentId);
       Log.d("DEBUG", ""+trueAnswers);
    }

    public void setWordsToTrain(ArrayList<String> temp){
        int count = temp.size();
        wordsToTrain = new String[5];
        wordsToTrainId =  arrayRandom(count);
        for (int i = 0; i < 5; i++) {
            wordsToTrain[i] = temp.get(wordsToTrainId[i]-1);
            Log.d("DEBUG: words to train", "" + wordsToTrain[i]);
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
    private ArrayList<String> TranslationArray(){
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            for (int i = 0; i < ArrayHelper().size(); i++) {
                arrayList.add(TranslateYandex(wordsToTrain[i], "en-ru"));
            }}catch(Exception e){
            e.printStackTrace();
        }
        return arrayList;
    }

    public String TranslateYandex(String textToBeTranslated, String languagePair) {
        YandexTranslate translatorBackgroundTask = new YandexTranslate();
        return translatorBackgroundTask.doInBackground(textToBeTranslated, languagePair);
    }

    public static int getTrueAnswers() {
        return trueAnswers;
    }
}