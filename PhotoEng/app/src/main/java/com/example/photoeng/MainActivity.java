package com.example.photoeng;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
       // ImageView.setImageBitmap(bitmap);
        TessOCR tess = new TessOCR();
        //CameraText.setText(tess.getOCRResult(bitmap));
    }
    public class TessOCR {
        private TessBaseAPI mTess;

//        public TessOCR() {
//            // TODO Auto-generated constructor stub
//            mTess = new TessBaseAPI();
//            try {
//                String datapath = Environment.getExternalStorageDirectory() + "/eng.traineddata/";
//                String language = "eng";
//                File dir = new File(datapath + "eng.traineddata/");
//                if (!dir.exists())
//                    dir.mkdirs();
//
//            }catch(Exception e){
//                e.printStackTrace();
//                Log.i("wwwwwwwww",e.toString());
//            }
//        }

        public String getOCRResult(Bitmap bitmap) {

            mTess.setImage(bitmap);
            String result = mTess.getUTF8Text();

            return result;
        }

        public void onDestroy() {
            if (mTess != null)
                mTess.end();
        }
      /*  public boolean init(String datapath, String language) {
            if (datapath == null) {
                throw new IllegalArgumentException("Data path must not be null!");
            }
            if (!datapath.endsWith(File.separator)) {
                datapath += File.separator;
            }

            File tessdata = new File(datapath + "tessdata");
            if (!tessdata.exists() || !tessdata.isDirectory()) {
                throw new IllegalArgumentException("Data path must contain subfolder tessdata!");
            }

            return nativeInit
        }*/

    }
}