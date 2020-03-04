package com.example.photoeng;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.Locale;

import static com.example.photoeng.DictionaryToSay.CHANNEL_ID;


public class LearningService extends Service {

    private TextToSpeech TTS;
    private  TextToSpeech TTS2;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ArrayList<String> temp1 = intent.getStringArrayListExtra("inputExtraArray1");
        ArrayList<String> temp2 = intent.getStringArrayListExtra("inputExtraArray2");

        Intent intent1 = new Intent(this, MainScreen.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent1, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Test service")
                .setContentText("Learning words")
                .setSmallIcon(R.drawable.ic_whatshot)
                .setContentIntent(pendingIntent)
                .build();



        TTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = TTS.setLanguage(Locale.UK);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {

                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });
        TTS2 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    Locale locale = new Locale("ru");
                    int result = TTS.setLanguage(locale);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {

                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });


        for(int i = 0; i<temp1.size(); i++){
            TTS.speak(temp1.get(i),TextToSpeech.QUEUE_FLUSH, null);
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            TTS2.speak(temp2.get(i), TextToSpeech.QUEUE_FLUSH, null);
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        startForeground(1, notification);

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
