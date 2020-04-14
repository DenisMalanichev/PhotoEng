package com.example.photoeng;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.ArrayList;
import java.util.Locale;

import static com.example.photoeng.MainScreen.SPEECH_ARRAY_MESSAGE;


public class HelloService extends Service {

    private String str;
    private static TextToSpeech mTts;
    private static TextToSpeech mTtsRU;
    private static final String TAG = "TTSService";

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        mTts = new TextToSpeech(this,
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        mTts.setSpeechRate(0.7f);
                        mTts.setLanguage(Locale.US);
                    }
                }
        );
        mTtsRU = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                mTts.setSpeechRate(1.0f);
                mTts.setLanguage(new Locale("ru"));
            }
        });
        Log.v(TAG, "oncreate_service");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        if (mTts != null) {
            mTts.stop();
            mTts.shutdown();
        }
        if (mTtsRU != null) {
            mTtsRU.stop();
            mTtsRU.shutdown();
        }
       // DictionaryForLearningActivity.getWordsToLearn().clear();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        final ArrayList<String> stringArrayExtra = intent.getStringArrayListExtra(SPEECH_ARRAY_MESSAGE);

        if (!mTts.isSpeaking()) {
            say(stringArrayExtra);
        }

        Log.v(TAG, "onstart_service");
        return startId;
    }

    public static void say(ArrayList<String> str) {
            sayingLoop(str);

    }
    public static void stop(){
        mTts.stop();
    }

    public static boolean sayingLoop(ArrayList<String> str){
      for(int j = 0; j<10; j++) {
          for (int i = 0; i < str.size(); i++) {
              mTtsRU.speak(str.get(i),
                      TextToSpeech.QUEUE_ADD,
                      null);
              i++;
              mTts.speak(str.get(i),
                      TextToSpeech.QUEUE_ADD, null);

          }
      }
        return false;
    }
}
