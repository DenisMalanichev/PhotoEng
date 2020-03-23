package com.example.photoeng;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;


import java.util.ArrayList;
import java.util.Locale;

import static android.content.Intent.getIntent;
import static android.os.Process.THREAD_PRIORITY_BACKGROUND;
import static com.example.photoeng.DictionaryToSay.CHANNEL_ID;

public class HelloService extends Service {

    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;
    private  Notification notification;

  // Handler that receives messages from the thread
  private final class ServiceHandler extends Handler {


      public ServiceHandler(Looper looper) {
          super(looper);
      }
    /*  @Override
      public void handleMessage(Message msg) {

          post(new Runnable() {
              @Override
              public void run() {
                  Intent intent = new Intent();
                  SaingDictionary.saying(intent, getApplicationContext());
              }
          });




          stopSelf(msg.arg1);
      }*/
  }

  @Override
  public void onCreate() {
    // Start up the thread running the service.  Note that we create a
    // separate thread because the service normally runs in the process's
    // main thread, which we don't want to block.  We also make it
    // background priority so CPU-intensive work will not disrupt our UI.
    HandlerThread thread = new HandlerThread("ServiceStartArguments",
           THREAD_PRIORITY_BACKGROUND);
    thread.start();

    // Get the HandlerThread's Looper and use it for our Handler
    mServiceLooper = thread.getLooper();
    mServiceHandler = new ServiceHandler(mServiceLooper);
      Intent intent1 = new Intent(this, MainScreen.class);
      PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
              intent1, 0);
      notification = new NotificationCompat.Builder(this, CHANNEL_ID)
              .setContentTitle("Test service")
              .setContentText("Learning words")
              .setSmallIcon(R.drawable.ic_whatshot)
              .setContentIntent(pendingIntent)
              .build();
      startForeground(1, notification);

  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
      Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

      // For each start request, send a message to start a job and deliver the
      // start ID so we know which request we're stopping when we finish the job
      Message msg = mServiceHandler.obtainMessage();
      msg.arg1 = startId;
      mServiceHandler.sendMessage(msg);


      // If we get killed, after returning from here, restart
      return START_STICKY;
  }

  @Override
  public IBinder onBind(Intent intent) {
      // We don't provide binding, so return null
      return null;
  }

  @Override
  public void onDestroy() {
    Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
  }
}
