package com.example.photoeng;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private Button BackButton;
    private TextView CameraText;
    private ImageButton ScaneButton;
    private ImageView ImageView;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    private ImageButton SayButton;
    public String returnedStringData;
    TesseractOCR tesseractOCR;
    int count =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            BackButton =  findViewById(R.id.Back_button);
            CameraText =  findViewById(R.id.Camera_text);
            ScaneButton =  findViewById(R.id.scane_button);
            ImageView =  findViewById(R.id.image);
            SayButton = findViewById(R.id.say_button);
        }catch (Exception e){
            e.printStackTrace();
        }

        BackButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainScreen.class);
                startActivity(intent);
            }
        });

        ScaneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
                count = 1;
            }
        });
        SayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
                count = 2;
            }
        });
    }

    private String extractText(Bitmap bitmap, Context context) throws Exception{
        String dstPathDir = "/tesseract/tessdata/";
        String srcFile = "eng.traineddata";
        InputStream inFile = null;
        dstPathDir = context.getFilesDir() + dstPathDir;
        String dstInitPathDir = context.getFilesDir() + "/tesseract";
        String dstPathFile = dstPathDir + srcFile;
        TessBaseAPI tessBaseApi = new TessBaseAPI();
        tessBaseApi.init((context.getFilesDir() + dstPathDir), "eng");
        tessBaseApi.setImage(bitmap);
        String extractedText = tessBaseApi.getUTF8Text();
        tessBaseApi.end();
        return extractedText;
    }
    public void speak() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.UK);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Ваша фраза:");

        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(count == 2) {
            Intent intent = new Intent(this, MainScreen.class);
            count =0;
            try {
                switch (requestCode) {
                    case REQUEST_CODE_SPEECH_INPUT: {
                        if (resultCode == RESULT_OK && null != data) {
                            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                            //CameraText.setText(result.get(0));
                            intent.putExtra("extraString", result.get(0));
                            startActivity(intent);
                        }
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else if(count == 1){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            count =0;
            try {
                tesseractOCR = new TesseractOCR(this, "eng");
                CameraText.setText(tesseractOCR.getOCRResult(bitmap));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

