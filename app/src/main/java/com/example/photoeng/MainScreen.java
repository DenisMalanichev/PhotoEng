package com.example.photoeng;


        import android.content.Context;
        import android.content.Intent;
        import android.content.pm.ActivityInfo;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.inputmethodservice.InputMethodService;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.speech.tts.TextToSpeech;
        import android.util.Log;
        import android.view.KeyEvent;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;

        import com.example.photoeng.data.DBHelper;
        import com.google.android.material.bottomnavigation.BottomNavigationView;
        import com.google.android.material.floatingactionbutton.FloatingActionButton;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.net.URLConnection;
        import java.util.ArrayList;
        import java.util.Locale;

        import static android.database.DatabaseUtils.queryNumEntries;


public class MainScreen extends MainActivity {

    private ImageButton Translate;
    private static EditText TextReader;
    private static TextView TranslatedWord;
    private ImageButton SayButton;
    private TextToSpeech TTS;
    private static final ArrayList<String> words = new ArrayList<>();
    public final static String SPEECH_ARRAY_MESSAGE = "speech_array";
    private DBHelper mDBHelper;
    private BottomNavigationView mBottomNavigationView;
    private FloatingActionButton FloatingButton;
    private Button setLangButton;
    private InternetConnection ic = new InternetConnection();
    public String[] linesArrayA;
    public String[] linesArrayB;
    public String[] linesArrayC;
    public String[] linesArrayD;
    public String[] linesArrayE;
    public String[] linesArrayF;
    public String[] linesArrayG;
    public String[] linesArrayH;
    public String[] linesArrayI;
    public String[] linesArrayJ;
    public String[] linesArrayK;
    public String[] linesArrayL;
    public String[] linesArrayM;
    public String[] linesArrayN;
    public String[] linesArrayO;
    public String[] linesArrayP;
    public String[] linesArrayQ;
    public String[] linesArrayR;
    public String[] linesArrayS;
    public String[] linesArrayT;
    public String[] linesArrayU;
    public String[] linesArrayV;
    public String[] linesArrayW;
    public String[] linesArrayX;
    public String[] linesArrayY;
    public String[] linesArrayZ;
    private boolean isEng = true;
    public boolean isAliveSite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Translate =  findViewById(R.id.research_button);
        TextReader =  findViewById(R.id.text_reader);
        TranslatedWord =  findViewById(R.id.translated_word);
        SayButton =  findViewById(R.id.say);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        FloatingButton = findViewById(R.id.floating_button);
        setLangButton = findViewById(R.id.language_set_button);

        getExtras();
        try {
            Task task = new Task();
            task.execute();
            Log.d("DEBUG", "isAlive is " + isAliveSite);
        }catch (Exception e){
            e.printStackTrace();
        }
        mBottomNavigationView.setSelectedItemId(R.id.home_navigation_view);


        TextReader.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    Translate.performClick();
                    Translate.callOnClick();
                    InputMethodService ims = (InputMethodService)getSystemService(Context.INPUT_METHOD_SERVICE);
                    ims.hideStatusIcon();

                    return false;
                }
                return false;
            }
        });
        setLangButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEng) {
                    isEng = false;
                    setLangButton.setText(R.string.ru);
                }else{
                    isEng = true;
                    setLangButton.setText(R.string.eng);
                }
            }
        });
        FloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbhelper = new DBHelper(MainScreen.this);
                String word =  TextReader.getText().toString().trim().toLowerCase();
                String translation = TranslatedWord.getText().toString().toLowerCase().trim();
                Log.d("TEST", ""+translation.trim().length());
                if(word.length() != 0) {
                    String translatedWord ;
                            if(isEng) {
                                translatedWord = translate(word);
                            }else {
                                translatedWord = translateRu(word);
                            }
                    ArrayList<String> arrayList = getDBDataEng(MainScreen.this);
                    boolean isWordExistInDB = false;
                    for(int i=0; i < arrayList.size(); i++){
                        if(word.equals(arrayList.get(i))){
                            Toast.makeText(MainScreen.this, "Такое слово уже есть", Toast.LENGTH_SHORT).show();
                            isWordExistInDB = true;
                            break;
                        }
                    }
                    if(isWordExistInDB == false) {
                        dbhelper.addWordsToDB(word, translatedWord);
                        dbhelper.close();
                        Toast.makeText(MainScreen.this, "Добавлено", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(MainScreen.this, "Вы еще ничего не перевели", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.dictionary_navigation_view:
                        Intent intent = new Intent(MainScreen.this, DictionaryActivity.class);
                        DictionaryActivity.setTemp(getDBDataEng(MainScreen.this));
                        DictionaryActivity.setTemp2(getDBDataRu(MainScreen.this));
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.learn_navigation_view:
                        if(getDBSize() > 0) {
                            Intent intentLearn = new Intent(MainScreen.this, DictionaryForLearningActivity.class);
                            DictionaryForLearningActivity.setTemp(getDBDataEng(MainScreen.this));
                            DictionaryForLearningActivity.setTemp2(getDBDataRu(MainScreen.this));
                            startActivity(intentLearn);
                        }else{
                            Toast.makeText(MainScreen.this, "У вас еще нет слов для изучения", Toast.LENGTH_LONG).show();
                        }
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.train_navigation_view:
                        if(getDBSize() > 4) {
                            Intent intentTrain = new Intent(MainScreen.this, TrainingActivity.class);
                            startActivity(intentTrain);
                            overridePendingTransition(0, 0);
                        }else{
                            Toast.makeText(MainScreen.this, "Нужно иметь минимум 5 слов", Toast.LENGTH_SHORT).show();
                        }
                        return true;

                }
                return false;
            }
        });


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




        Translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEng) {
                    translate(TextReader.getText().toString().trim());//delete "_" in text
                }else {
                    translateRu(TextReader.getText().toString().trim());
                }
            }

        });

        SayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TTS.speak(TextReader.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });


        final Intent serviceIntent = new Intent(MainScreen.this, HelloService.class);
        serviceIntent.putExtra(SPEECH_ARRAY_MESSAGE, words);
        startService(serviceIntent);

    }

    @Override
    protected void onDestroy() {
        if (TTS != null) {
            TTS.stop();
            TTS.shutdown();
        }
        super.onDestroy();

    }





    public String TranslateYandex(String textToBeTranslated, String languagePair) {
        YandexTranslate translatorBackgroundTask = new YandexTranslate();
        return translatorBackgroundTask.doInBackground(textToBeTranslated, languagePair);
    }


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity == null) {
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
                    return true;
                }
            }
        }
        return false;
    }

    public void getExtras(){
        Intent intent = getIntent();
        String extra = intent.getStringExtra("extraString");

        if(extra != "") {
            TextReader.setText(extra);
        }
    }
    public static String getWord(){
        return TranslatedWord.getText().toString().trim();
    }



    public long getDBSize(){
        mDBHelper = new DBHelper(MainScreen.this);
        SQLiteDatabase database = mDBHelper.getWritableDatabase();
        long dbSize = queryNumEntries(database, mDBHelper.TABLE_CONTACTS, null);
        Log.d("TEST", ""+dbSize);
        return dbSize;
    }
    public ArrayList<String> getDBDataEng(Context context){
        mDBHelper = new DBHelper(MainScreen.this);
          SQLiteDatabase database = mDBHelper.getReadableDatabase();
          Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null,
                        null, null, null, null);
        ArrayList<String>  listEng = new ArrayList<>();
          try {
              int nameIndex = cursor.getColumnIndex(DBHelper.KEY_WORDS);

              if (cursor.moveToNext()) {

                  do {
                      listEng.add(cursor.getString(nameIndex));
                  } while (cursor.moveToNext());
                  cursor.close();
              }
          }catch (Exception e){
              e.printStackTrace();
          }
        return listEng;
    }
    public ArrayList<String> getDBDataRu(Context context){
        mDBHelper = new DBHelper(MainScreen.this);
        SQLiteDatabase database = mDBHelper.getReadableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null,
                null, null, null, null);


        int nameIndex = cursor.getColumnIndex(DBHelper.KEY_TRANSLATION);
        ArrayList<String> listRu = new ArrayList<>();
        if(cursor.moveToNext()) {

            do {
                listRu.add(cursor.getString(nameIndex));
            } while (cursor.moveToNext());
            cursor.close();
            }
        return listRu;
        }

        public String translate(String word){
        String translation;
        if(isNetworkAvailable(MainScreen.this) && isAliveSite){

                        try {
                            translation = TranslateYandex(word, "en-ru");
                            TranslatedWord.setText(translation);
                            return translation;
                        } catch (Exception e) {
                            initialize();
                            TranslatedWord.setText(translationNoInternet(word));
                                Toast.makeText(this, "Нет доступа к Yandex", Toast.LENGTH_SHORT).show();
                            return translationNoInternet(word);
                        }
            }else{
                try {
                    Log.d("DEBUG", "no internet");
                    initialize();
                    TranslatedWord.setText(translationNoInternet(word));
                    return translationNoInternet(word);
                } catch (Exception e) {
                    e.printStackTrace();
                }}
            return null;
        }

        public String translateRu(String word){
            String translation;

            if(isNetworkAvailable(MainScreen.this)){
                try {
                    translation = TranslateYandex(word, "ru-en");
                    TranslatedWord.setText(translation);
                    return translation;
                } catch (Exception e) {
                    e.printStackTrace();
                }}else{
                try {
                    Log.d("DEBUG", "no internet");
                    initialize();
                    TranslatedWord.setText(translationNoInternet(word));
                    return translationNoInternet(word);
                } catch (Exception e) {
                    e.printStackTrace();
                }}
            return null;
        }




    public void initialize() {
        //Scanning files
        try {
            Log.d("DEBUG", "no internet1");
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

            InputStream fileR =this.getResources().openRawResource(R.raw.r_eng_rus);
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
    }
    public String translationNoInternet(String word) {
        char[] wordArray = word.toCharArray();
        if (wordArray[0] == 'a') {
            Log.d("DEBUG", "no internet2");
            try {
                for (int i = 0; i < linesArrayA.length; i++) {
                    if (word.equals(linesArrayA[i])) {
                        return linesArrayA[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'b') {
            try {
                for (int i = 0; i < linesArrayB.length; i++) {
                    if (word.equals(linesArrayB[i])) {
                        return linesArrayB[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'c') {
            try {
                for (int i = 0; i < linesArrayC.length; i++) {
                    if (word.equals(linesArrayC[i])) {
                        return linesArrayC[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'd') {
            try {
                for (int i = 0; i < linesArrayD.length; i++) {
                    if (word.equals(linesArrayD[i])) {
                        return linesArrayD[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'e') {
            try {
                for (int i = 0; i < linesArrayE.length; i++) {
                    if (word.equals(linesArrayE[i])) {
                        return linesArrayE[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'f') {
            try {
                for (int i = 0; i < linesArrayF.length; i++) {
                    if (word.equals(linesArrayF[i])) {
                        return linesArrayF[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'g') {
            try {
                for (int i = 0; i < linesArrayG.length; i++) {
                    if (word.equals(linesArrayG[i])) {
                        return linesArrayG[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'h') {
            try {
                for (int i = 0; i < linesArrayH.length; i++) {
                    if (word.equals(linesArrayH[i])) {
                        return linesArrayH[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'i') {
            try {
                for (int i = 0; i < linesArrayI.length; i++) {
                    if (word.equals(linesArrayI[i])) {
                        return linesArrayI[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'j') {
            try {
                for (int i = 0; i < linesArrayJ.length; i++) {
                    if (word.equals(linesArrayJ[i])) {
                        return linesArrayJ[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'k') {
            try {
                for (int i = 0; i < linesArrayK.length; i++) {
                    if (word.equals(linesArrayK[i])) {
                        return linesArrayK[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'l') {
            try {
                for (int i = 0; i < linesArrayL.length; i++) {
                    if (word.equals(linesArrayL[i])) {
                        return linesArrayL[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'm') {
            try {
                for (int i = 0; i < linesArrayM.length; i++) {
                    if (word.equals(linesArrayM[i])) {
                        return linesArrayM[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'n') {
            try {
                for (int i = 0; i < linesArrayN.length; i++) {
                    if (word.equals(linesArrayN[i])) {
                        return linesArrayN[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'o') {
            try {
                for (int i = 0; i < linesArrayO.length; i++) {
                    if (word.equals(linesArrayO[i])) {
                        return linesArrayO[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'p') {
            try {
                for (int i = 0; i < linesArrayP.length; i++) {
                    if (word.equals(linesArrayP[i])) {
                        return linesArrayP[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'q') {
            try {
                for (int i = 0; i < linesArrayQ.length; i++) {
                    if (word.equals(linesArrayQ[i])) {
                        return linesArrayQ[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'r') {
            try {
                for (int i = 0; i < linesArrayR.length; i++) {
                    if (word.equals(linesArrayR[i])) {
                        return linesArrayR[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 's') {
            try {
                for (int i = 0; i < linesArrayS.length; i++) {
                    if (word.equals(linesArrayS[i])) {
                        return linesArrayS[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 't') {
            try {
                for (int i = 0; i < linesArrayT.length; i++) {
                    if (word.equals(linesArrayT[i])) {
                        return linesArrayT[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'u') {
            try {
                for (int i = 0; i < linesArrayU.length; i++) {
                    if (word.equals(linesArrayU[i])) {
                        return linesArrayU[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'v') {
            try {
                for (int i = 0; i < linesArrayV.length; i++) {
                    if (word.equals(linesArrayV[i])) {
                        return linesArrayV[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'w') {
            try {
                for (int i = 0; i < linesArrayW.length; i++) {
                    if (word.equals(linesArrayW[i])) {
                        return linesArrayW[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'x') {
            try {
                for (int i = 0; i < linesArrayX.length; i++) {
                    if (word.equals(linesArrayX[i])) {
                        return linesArrayX[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'y') {
            try {
                for (int i = 0; i < linesArrayY.length; i++) {
                    if (word.equals(linesArrayY[i])) {
                        return linesArrayY[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'z') {
            try {
                for (int i = 0; i < linesArrayZ.length; i++) {
                    if (word.equals(linesArrayZ[i])) {
                       return  linesArrayA[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            return "Слова нет в словаре, подключите интернет";
    }

    class Task extends AsyncTask<Boolean, Boolean, Boolean> {

        public boolean isAlive;

        @Override
        protected Boolean doInBackground(Boolean... booleans){
            isAlive = isSiteAvail("https://tech.yandex.com/translate/");
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            setAliveSite(isAlive);
        }

        boolean isSiteAvail(String site) {
            try {
                URL url = new URL(site);
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(5000);
                urlc.connect();
                int Code = urlc.getResponseCode();
                if (Code == HttpURLConnection.HTTP_OK) ;
                Log.d("IJOIOIJ", "true");
                return true;
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
            Log.d("IJOIOIJ", "false");
            return false;
        }


    }

    public void setAliveSite(boolean aliveSite) {
        isAliveSite = aliveSite;
    }
}

