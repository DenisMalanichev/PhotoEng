package com.example.photoeng;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private Button ScaneButton;
    private ImageView ImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BackButton = (Button) findViewById(R.id.Back_button);
        CameraText = (TextView) findViewById(R.id.Camera_text);
        ScaneButton = (Button) findViewById(R.id.scane_button);
        ImageView = (ImageView) findViewById(R.id.image);

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
            CameraText.setText(extractText(bitmap));
            Log.d("TESS", extractText(bitmap));
        }catch (Exception e){
            e.printStackTrace();
        }

        //TODO tesseract(find text on photo)

    }
    private String extractText(Bitmap bitmap) throws Exception{
        TessBaseAPI tessBaseApi = new TessBaseAPI();
        tessBaseApi.init("/mnt/sdcard/tesseract/tessdata/eng.trainedata", "eng");
        tessBaseApi.setImage(bitmap);
        String extractedText = tessBaseApi.getUTF8Text();
        tessBaseApi.end();
        return extractedText;
    }


}

