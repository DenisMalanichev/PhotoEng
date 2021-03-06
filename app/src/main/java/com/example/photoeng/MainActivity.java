package com.example.photoeng;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.photoeng.adapters.IntroAdapter;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private Button BackButton;
    private TextView CameraText;
    private ImageButton ScaneButton;
    private ImageView ImageView;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    private ImageButton SayButton;
    private Button toTranslate;
    private ViewPager mViewPager;
    Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


            BackButton = findViewById(R.id.Back_button);
            CameraText = findViewById(R.id.Camera_text);
            ScaneButton = findViewById(R.id.scane_button);
            ImageView = findViewById(R.id.image);
            SayButton = findViewById(R.id.say_button);
            toTranslate = findViewById(R.id.totranslate);
            mViewPager = findViewById(R.id.viewPager);



        toTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CameraText.getText().toString().equals("Наведите камеру на текст, который нужно перевести")) {
                    Toast.makeText(MainActivity.this, "Вы еще ничего не просканировали", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent1 = new Intent(MainActivity.this, MainScreen.class);
                    intent1.putExtra("extraString", CameraText.getText().toString());
                    startActivity(intent1);
                }
            }
        });
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
                CropImage.startPickImageActivity(MainActivity.this);
            }
        });
        SayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
    }

    public void speak() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        Locale locale = new Locale("en");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Ваша фраза:");

        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

            Intent intent = new Intent(this, MainScreen.class);
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



            if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                Uri imageuri = CropImage.getPickImageResultUri(this, data);
                if (CropImage.isReadExternalStoragePermissionsRequired(this, imageuri)) {
                    uri = imageuri;
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                } else {
                    startCrop(imageuri);
                }
            }

            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {

                    ImageView.setImageURI(result.getUri());
                    final TesseractOCR tesseract = new TesseractOCR(this, "eng");
                    CameraText.setText(tesseract.getOCRResult(imageView2Bitmap(ImageView)));
                    Toast.makeText(this, "working", Toast.LENGTH_LONG).show();
                }
            }
        }




    private void startCrop(Uri uri) {
        CropImage.activity(uri).setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);
    }
    private Bitmap imageView2Bitmap(ImageView view){
        Bitmap bitmap = ((BitmapDrawable)view.getDrawable()).getBitmap();
        return bitmap;
    }

}