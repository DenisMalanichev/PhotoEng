package com.example.photoeng;


import android.widget.Toast;

import java.net.URL;
import java.net.URLConnection;

public class InternetConnection extends Thread {
    @Override
    public void run() {
        isConnectedToServer(30000);
    }


    public static boolean isConnectedToServer(final int timeout) {
                try{
                    URL myUrl = new URL("https://www.google.com/");
                    URLConnection connection = myUrl.openConnection();
                    connection.setConnectTimeout(timeout);
                    connection.connect();
                } catch (Exception e) {
                    return false;
                }

     return true;
    }
}
