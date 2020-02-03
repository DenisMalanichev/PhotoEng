package com.example.photoeng;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import net.sourceforge.tess4j.Tesseract;



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
        ImageView.setImageBitmap(bitmap);
        Tesseract tesseract = new Tesseract();

        //TODO tesseratc(find text on photo)

    }
}
