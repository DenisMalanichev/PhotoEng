package com.example.photoeng;


        import android.content.ContentValues;
        import android.content.Context;
        import android.content.Intent;
        import android.content.pm.ActivityInfo;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.os.Bundle;
        import android.os.Handler;
        import android.speech.tts.TextToSpeech;
        import android.util.Log;
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

        import java.util.ArrayList;
        import java.util.Locale;


public class MainScreen extends MainActivity {

    private ImageButton Translate;
    private static EditText TextReader;
    private static TextView TranslatedWord;
    private ImageButton SayButton;
    private Button SaveButton;
    private TextToSpeech TTS;
    private static final ArrayList<String> words = new ArrayList<>();
    private OfflineTranslateThread OTT = new OfflineTranslateThread();
    public final static String SPEECH_ARRAY_MESSAGE = "speech_array";
    public final static String EXTRA_KEY_DICTIONARY_ACTIVITY = "task_number_for_dictionary";
    private DBHelper mDBHelper;
    private BottomNavigationView mBottomNavigationView ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        Translate =  findViewById(R.id.research_button);
        TextReader =  findViewById(R.id.text_reader);
        TranslatedWord =  findViewById(R.id.translated_word);
        SaveButton = findViewById(R.id.save_button);
        SayButton =  findViewById(R.id.say);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        getExtras();

        mBottomNavigationView.setSelectedItemId(R.id.home_navigation_view);

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
                        if(getDBSize() == true) {
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
                        Intent intentTrain = new Intent(MainScreen.this, TrainingActivity.class);
                        startActivity(intentTrain);
                        overridePendingTransition(0,0);
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
                translate(TextReader.getText().toString().trim());//delete "_" in text
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
                DBHelper dbhelper = new DBHelper(MainScreen.this);
                String word =  TextReader.getText().toString().trim().toLowerCase();
                String translation = TranslatedWord.getText().toString().toLowerCase().trim();
                Log.d("TEST", ""+translation.trim().length());
                if(word.length() != 0 && translation.length() != 0) {
                    dbhelper.addWordsToDB(word, translation);
                    dbhelper.close();
                }else if(word.length() != 0 && translation.trim().length() == 0){
                    String translatedWord = translate(word);
                    Log.d("TEST", ""+translatedWord);
                    dbhelper.addWordsToDB(word, translatedWord);
                    dbhelper.close();
                }else {
                    Toast.makeText(MainScreen.this, "Вы еще ничего не перевели", Toast.LENGTH_SHORT).show();
                }
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

    public static ArrayList getWords() {
        return words;
    }

    public boolean getDBSize(){
        mDBHelper = new DBHelper(MainScreen.this);
        SQLiteDatabase database = mDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_WORDS, TextReader.getText().toString());
        database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);

        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null,
                null, null, null, null);


            if(cursor.moveToPosition(1) == true) {
                return true;
            }



        return false;
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
            if(isNetworkAvailable(MainScreen.this)){
                try {
                    translation = TranslateYandex(word, "en-ru");
                    TranslatedWord.setText(translation);
                    return translation;
                } catch (Exception e) {
                    e.printStackTrace();
                }}else{
                try {
                    OTT.translationNoInternet(word);
                    translation = OTT.Answer;
                    return translation;
                } catch (Exception e) {
                    e.printStackTrace();
                }}
        return null;
        }
    }

