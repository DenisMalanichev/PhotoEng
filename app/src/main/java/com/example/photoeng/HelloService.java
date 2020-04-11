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
        StringBuilder strToSay = new StringBuilder();
        for (String word : str) {

            strToSay.append(word);

        }
        mTts.speak(strToSay.toString(),
                TextToSpeech.QUEUE_FLUSH,
                null);

    }
    public static void stop(){
        mTts.stop();
    }
}
