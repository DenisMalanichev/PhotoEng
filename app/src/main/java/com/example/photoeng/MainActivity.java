package com.example.photoeng;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MainActivity extends AppCompatActivity {

    private Button BackButton;
    private TextView CameraText;
    private ImageButton ScaneButton;
    private ImageView ImageView;
    private TesseractOCR tesseractOCR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            BackButton =  findViewById(R.id.Back_button);
            CameraText =  findViewById(R.id.Camera_text);
            ScaneButton =  findViewById(R.id.scane_button);
            ImageView =  findViewById(R.id.image);
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
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
       // ImageView.setImageBitmap(bitmap);
        try {
           tesseractOCR = new TesseractOCR(this, "eng");
           CameraText.setText(tesseractOCR.getOCRResult(bitmap));
        }catch (Exception e){
            e.printStackTrace();
        }
        //TODO tesseract(find text on photo)

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
}

