package com.example.photoeng;


        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.graphics.Bitmap;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.os.Bundle;
        import android.speech.tts.TextToSpeech;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.googlecode.tesseract.android.TessBaseAPI;
        import java.io.BufferedReader;
        import java.io.File;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.util.Locale;


public class MainScreen extends MainActivity {

    private ImageButton Translate;
    private EditText TextReader;
    private TextView TranslatedWord;
    public String[] linesArrayA;
    private String[] linesArrayB;
    private String[] linesArrayC;
    private String[] linesArrayD;
    private String[] linesArrayE;
    private String[] linesArrayF;
    private String[] linesArrayG;
    private String[] linesArrayH;
    private String[] linesArrayI;
    private String[] linesArrayJ;
    private String[] linesArrayK;
    private String[] linesArrayL;
    private String[] linesArrayM;
    private String[] linesArrayN;
    private String[] linesArrayO;
    private String[] linesArrayP;
    private String[] linesArrayQ;
    private String[] linesArrayR;
    private String[] linesArrayS;
    private String[] linesArrayT;
    private String[] linesArrayU;
    private String[] linesArrayV;
    private String[] linesArrayW;
    private String[] linesArrayX;
    private String[] linesArrayY;
    private String[] linesArrayZ;
    private ImageButton SayButton;
    private Button SaveButton;
    private  Button ShowButton;
    private TextToSpeech TTS;
    DBHelper dbhelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        Translate =  findViewById(R.id.research_button);
        TextReader =  findViewById(R.id.text_reader);
        TranslatedWord =  findViewById(R.id.translated_word);
        SaveButton = findViewById(R.id.save_button);
        ShowButton = findViewById(R.id.show_button);
        SayButton =  findViewById(R.id.say);
        dbhelper = new DBHelper(this);
        SQLiteDatabase database;

        TTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = TTS.setLanguage(Locale.UK);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        SayButton.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        //Scanning files
        try {
            InputStream fileA = this.getResources().openRawResource(R.raw.a_eng_rus);
            BufferedReader readerA = new BufferedReader(new InputStreamReader(fileA));
            linesArrayA = new String[9156];
            for (int i = 0; i < 9156; i++) {
                linesArrayA[i] = readerA.readLine();
            }
            fileA.close();
            readerA.close();

            InputStream fileB = this.getResources().openRawResource(R.raw.b_eng_rus);
            BufferedReader readerB = new BufferedReader(new InputStreamReader(fileB));
            linesArrayB = new String[4613];
            for (int q = 0; q < 4613; q++) {
                linesArrayB[q] = readerB.readLine();
            }
            fileB.close();
            readerB.close();

            InputStream fileC = this.getResources().openRawResource(R.raw.c_eng_rus);
            BufferedReader readerC = new BufferedReader(new InputStreamReader(fileC));
            linesArrayC = new String[8416];
            for (int i = 0; i < 8416; i++) {
                linesArrayC[i] = readerC.readLine();
            }
            fileC.close();
            readerC.close();

            InputStream fileD = this.getResources().openRawResource(R.raw.d_eng_rus);
            BufferedReader readerD = new BufferedReader(new InputStreamReader(fileD));
            linesArrayD = new String[5601];
            for (int i = 0; i < 5601; i++) {
                linesArrayD[i] = readerD.readLine();
            }
            fileD.close();
            readerD.close();

            InputStream fileE = this.getResources().openRawResource(R.raw.e_eng_rus);
            BufferedReader readerE = new BufferedReader(new InputStreamReader(fileE));
            linesArrayE = new String[3818];
            for (int i = 0; i < 3818; i++) {
                linesArrayE[i] = readerE.readLine();
            }
            fileE.close();
            readerE.close();

            InputStream fileF = this.getResources().openRawResource(R.raw.f_eng_rus);
            BufferedReader readerF = new BufferedReader(new InputStreamReader(fileF));
            linesArrayF = new String[5601];
            for (int i = 0; i < 3571; i++) {
                linesArrayF[i] = readerF.readLine();
            }
            fileF.close();
            readerF.close();

            InputStream fileG = this.getResources().openRawResource(R.raw.g_eng_rus);
            BufferedReader readerG = new BufferedReader(new InputStreamReader(fileG));
            linesArrayG = new String[2263];
            for (int i = 0; i < 2263; i++) {
                linesArrayG[i] = readerG.readLine();
            }
            fileG.close();
            readerG.close();

            InputStream fileH = this.getResources().openRawResource(R.raw.h_eng_rus);
            BufferedReader readerH = new BufferedReader(new InputStreamReader(fileH));
            linesArrayH = new String[2689];
            for (int i = 0; i < 2689; i++) {
                linesArrayH[i] = readerH.readLine();
            }
            fileH.close();
            readerH.close();

            InputStream fileI = this.getResources().openRawResource(R.raw.i_eng_rus);
            BufferedReader readerI = new BufferedReader(new InputStreamReader(fileI));
            linesArrayI = new String[4003];
            for (int i = 0; i < 4003; i++) {
                linesArrayI[i] = readerI.readLine();
            }
            fileI.close();
            readerI.close();

            InputStream fileJ = this.getResources().openRawResource(R.raw.j_eng_rus);
            BufferedReader readerJ = new BufferedReader(new InputStreamReader(fileJ));
            linesArrayJ = new String[591];
            for (int i = 0; i < 591; i++) {
                linesArrayJ[i] = readerJ.readLine();
            }
            fileJ.close();
            readerJ.close();

            InputStream fileK = this.getResources().openRawResource(R.raw.k_eng_rus);
            BufferedReader readerK = new BufferedReader(new InputStreamReader(fileK));
            linesArrayK = new String[514];
            for (int i = 0; i < 514; i++) {
                linesArrayK[i] = readerK.readLine();
            }
            fileK.close();
            readerK.close();

            InputStream fileL = this.getResources().openRawResource(R.raw.l_eng_rus);
            BufferedReader readerL = new BufferedReader(new InputStreamReader(fileL));
            linesArrayL = new String[2641];
            for (int i = 0; i < 2641; i++) {
                linesArrayL[i] = readerL.readLine();
            }
            fileL.close();
            readerL.close();

            InputStream fileM = this.getResources().openRawResource(R.raw.m_eng_rus);
            BufferedReader readerM = new BufferedReader(new InputStreamReader(fileM));
            linesArrayM = new String[4551];
            for (int i = 0; i < 4551; i++) {
                linesArrayM[i] = readerM.readLine();
            }
            fileM.close();
            readerM.close();

            InputStream fileN = this.getResources().openRawResource(R.raw.n_eng_rus);
            BufferedReader readerN = new BufferedReader(new InputStreamReader(fileN));
            linesArrayN = new String[1787];
            for (int i = 0; i < 1787; i++) {
                linesArrayN[i] = readerN.readLine();
            }
            fileN.close();
            readerN.close();

            InputStream fileO = this.getResources().openRawResource(R.raw.o_eng_rus);
            BufferedReader readerO = new BufferedReader(new InputStreamReader(fileO));
            linesArrayO = new String[2215];
            for (int i = 0; i < 2215; i++) {
                linesArrayO[i] = readerO.readLine();
            }
            fileO.close();
            readerO.close();

            InputStream fileP = this.getResources().openRawResource(R.raw.p_eng_rus);
            BufferedReader readerP = new BufferedReader(new InputStreamReader(fileP));
            linesArrayP = new String[10006];
            for (int i = 0; i < 10006; i++) {
                linesArrayP[i] = readerP.readLine();
            }
            fileP.close();
            readerP.close();

            InputStream fileQ = this.getResources().openRawResource(R.raw.q_eng_rus);
            BufferedReader readerQ = new BufferedReader(new InputStreamReader(fileQ));
            linesArrayQ = new String[613];
            for (int i = 0; i < 613; i++) {
                linesArrayQ[i] = readerQ.readLine();
            }
            fileQ.close();
            readerQ.close();

            InputStream fileR = this.getResources().openRawResource(R.raw.r_eng_rus);
            BufferedReader readerR = new BufferedReader(new InputStreamReader(fileR));
            linesArrayR = new String[4947];
            for (int i = 0; i < 4947; i++) {
                linesArrayR[i] = readerR.readLine();
            }
            fileR.close();
            readerR.close();

            InputStream fileS = this.getResources().openRawResource(R.raw.s_eng_rus);
            BufferedReader readerS = new BufferedReader(new InputStreamReader(fileS));
            linesArrayS = new String[16305];
            for (int i = 0; i < 16305; i++) {
                linesArrayS[i] = readerS.readLine();
            }
            fileS.close();
            readerS.close();

            InputStream fileT = this.getResources().openRawResource(R.raw.t_eng_rus);
            BufferedReader readerT = new BufferedReader(new InputStreamReader(fileT));
            linesArrayT = new String[5993];
            for (int i = 0; i < 5993; i++) {
                linesArrayT[i] = readerT.readLine();
            }
            fileT.close();
            readerT.close();

            InputStream fileU = this.getResources().openRawResource(R.raw.u_eng_rus);
            BufferedReader readerU = new BufferedReader(new InputStreamReader(fileU));
            linesArrayU = new String[3421];
            for (int i = 0; i < 3421; i++) {
                linesArrayU[i] = readerU.readLine();
            }
            fileU.close();
            readerU.close();

            InputStream fileV = this.getResources().openRawResource(R.raw.v_eng_rus);
            BufferedReader readerV = new BufferedReader(new InputStreamReader(fileV));
            linesArrayV = new String[1311];
            for (int i = 0; i < 1311; i++) {
                linesArrayV[i] = readerV.readLine();
            }
            fileV.close();
            readerV.close();

            InputStream fileW = this.getResources().openRawResource(R.raw.w_eng_rus);
            BufferedReader readerW = new BufferedReader(new InputStreamReader(fileW));
            linesArrayW = new String[3071];
            for (int i = 0; i < 3071; i++) {
                linesArrayW[i] = readerW.readLine();
            }
            fileW.close();
            readerW.close();

            InputStream fileX = this.getResources().openRawResource(R.raw.x_eng_rus);
            BufferedReader readerX = new BufferedReader(new InputStreamReader(fileX));
            linesArrayX = new String[53];
            for (int i = 0; i < 53; i++) {
                linesArrayX[i] = readerX.readLine();
            }
            fileX.close();
            readerX.close();

            InputStream fileY = this.getResources().openRawResource(R.raw.y_eng_rus);
            BufferedReader readerY = new BufferedReader(new InputStreamReader(fileY));
            linesArrayY = new String[301];
            for (int i = 0; i < 301; i++) {
                linesArrayY[i] = readerY.readLine();
            }
            fileY.close();
            readerY.close();

            InputStream fileZ = this.getResources().openRawResource(R.raw.z_eng_rus);
            BufferedReader readerZ = new BufferedReader(new InputStreamReader(fileZ));
            linesArrayZ = new String[234];
            for (int i = 0; i < 234; i++) {
                linesArrayZ[i] = readerZ.readLine();
            }
            fileZ.close();
            readerZ.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        Translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //TODO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                String word = TextReader.getText().toString().trim();//delete "_" in text
                if(isNetworkAvailable(MainScreen.this)){
                try {
                    TranslatedWord.setText(TranslateYandex(word, "en-ru"));
                } catch (Exception e) {
                    e.printStackTrace();
                }}else{
                try {
                    translationNoInternet(word);
                } catch (Exception e) {
                    e.printStackTrace();
                }}

            }

        });
        SayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TTS.speak(TextReader.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);

            }
        });
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase database = dbhelper.getWritableDatabase();

                ContentValues contentValues = new ContentValues();

                contentValues.put(DBHelper.KEY_NAME, TextReader.getText().toString());
                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null,
                        null, null, null, null);
                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", name = " + cursor.getString(nameIndex));
                        Toast.makeText(MainScreen.this, cursor.getString(nameIndex), Toast.LENGTH_LONG).show();
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog","0 rows");
                database.delete(DBHelper.TABLE_CONTACTS, null, null);
                dbhelper.close();
                cursor.close();
            }
        });

    }

    @Override
    protected void onDestroy() {
        if (TTS != null) {
            TTS.stop();
            TTS.shutdown();
        }
        super.onDestroy();

    }
//TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


    public void translationNoInternet(String word) {
        char[] wordArray = word.toCharArray();
        if (wordArray[0] == 'a') {
            try {
                for (int i = 0; i < linesArrayA.length; i++) {
                    if (word.equals(linesArrayA[i])) {
                        TranslatedWord.setText(linesArrayA[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'b') {
            try {
                for (int i = 0; i < linesArrayB.length; i++) {
                    if (word.equals(linesArrayB[i])) {
                        TranslatedWord.setText(linesArrayB[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'c') {
            try {
                for (int i = 0; i < linesArrayC.length; i++) {
                    if (word.equals(linesArrayC[i])) {
                        TranslatedWord.setText(linesArrayC[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'd') {
            try {
                for (int i = 0; i < linesArrayD.length; i++) {
                    if (word.equals(linesArrayD[i])) {
                        TranslatedWord.setText(linesArrayD[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'e') {
            try {
                for (int i = 0; i < linesArrayE.length; i++) {
                    if (word.equals(linesArrayE[i])) {
                        TranslatedWord.setText(linesArrayE[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'f') {
            try {
                for (int i = 0; i < linesArrayF.length; i++) {
                    if (word.equals(linesArrayF[i])) {
                        TranslatedWord.setText(linesArrayF[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'g') {
            try {
                for (int i = 0; i < linesArrayG.length; i++) {
                    if (word.equals(linesArrayG[i])) {
                        TranslatedWord.setText(linesArrayG[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'h') {
            try {
                for (int i = 0; i < linesArrayH.length; i++) {
                    if (word.equals(linesArrayH[i])) {
                        TranslatedWord.setText(linesArrayH[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'i') {
            try {
                for (int i = 0; i < linesArrayI.length; i++) {
                    if (word.equals(linesArrayI[i])) {
                        TranslatedWord.setText(linesArrayI[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'j') {
            try {
                for (int i = 0; i < linesArrayJ.length; i++) {
                    if (word.equals(linesArrayJ[i])) {
                        TranslatedWord.setText(linesArrayJ[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'k') {
            try {
                for (int i = 0; i < linesArrayK.length; i++) {
                    if (word.equals(linesArrayK[i])) {
                        TranslatedWord.setText(linesArrayK[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'l') {
            try {
                for (int i = 0; i < linesArrayL.length; i++) {
                    if (word.equals(linesArrayL[i])) {
                        TranslatedWord.setText(linesArrayL[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'm') {
            try {
                for (int i = 0; i < linesArrayM.length; i++) {
                    if (word.equals(linesArrayM[i])) {
                        TranslatedWord.setText(linesArrayM[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'n') {
            try {
                for (int i = 0; i < linesArrayN.length; i++) {
                    if (word.equals(linesArrayN[i])) {
                        TranslatedWord.setText(linesArrayN[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'o') {
            try {
                for (int i = 0; i < linesArrayO.length; i++) {
                    if (word.equals(linesArrayO[i])) {
                        TranslatedWord.setText(linesArrayO[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'p') {
            try {
                for (int i = 0; i < linesArrayP.length; i++) {
                    if (word.equals(linesArrayP[i])) {
                        TranslatedWord.setText(linesArrayP[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'q') {
            try {
                for (int i = 0; i < linesArrayQ.length; i++) {
                    if (word.equals(linesArrayQ[i])) {
                        TranslatedWord.setText(linesArrayQ[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'r') {
            try {
                for (int i = 0; i < linesArrayR.length; i++) {
                    if (word.equals(linesArrayR[i])) {
                        TranslatedWord.setText(linesArrayR[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 's') {
            try {
                for (int i = 0; i < linesArrayS.length; i++) {
                    if (word.equals(linesArrayS[i])) {
                        TranslatedWord.setText(linesArrayS[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 't') {
            try {
                for (int i = 0; i < linesArrayT.length; i++) {
                    if (word.equals(linesArrayT[i])) {
                        TranslatedWord.setText(linesArrayT[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'u') {
            try {
                for (int i = 0; i < linesArrayU.length; i++) {
                    if (word.equals(linesArrayU[i])) {
                        TranslatedWord.setText(linesArrayU[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'v') {
            try {
                for (int i = 0; i < linesArrayV.length; i++) {
                    if (word.equals(linesArrayV[i])) {
                        TranslatedWord.setText(linesArrayV[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'w') {
            try {
                for (int i = 0; i < linesArrayW.length; i++) {
                    if (word.equals(linesArrayW[i])) {
                        TranslatedWord.setText(linesArrayW[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'x') {
            try {
                for (int i = 0; i < linesArrayX.length; i++) {
                    if (word.equals(linesArrayX[i])) {
                        TranslatedWord.setText(linesArrayX[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'y') {
            try {
                for (int i = 0; i < linesArrayY.length; i++) {
                    if (word.equals(linesArrayY[i])) {
                        TranslatedWord.setText(linesArrayY[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'z') {
            try {
                for (int i = 0; i < linesArrayZ.length; i++) {
                    if (word.equals(linesArrayZ[i])) {
                        TranslatedWord.setText(linesArrayZ[i + 1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public String TranslateYandex(String textToBeTranslated, String languagePair) {
        YandexTranslate translatorBackgroundTask = new YandexTranslate();
        return translatorBackgroundTask.doInBackground(textToBeTranslated, languagePair);
    }


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            Log.d("NetworkCheck", "isNetworkAvailable: No");
            return false;
        }

        // get network info for all of the data interfaces (e.g. WiFi, 3G, LTE, etc.)
        NetworkInfo[] info = connectivity.getAllNetworkInfo();

        // make sure that there is at least one interface to test against
        if (info != null) {
            // iterate through the interfaces
            for (int i = 0; i < info.length; i++) {
                // check this interface for a connected state
                if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                    Log.d("NetworkCheck", "isNetworkAvailable: Yes");
                    return true;
                }
            }
        }
        return false;
    }


}