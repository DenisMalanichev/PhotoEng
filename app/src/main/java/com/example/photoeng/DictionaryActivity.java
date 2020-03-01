package com.example.photoeng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DictionaryActivity extends AppCompatActivity {


    DBHelper dbhelper;
    private androidx.recyclerview.widget.RecyclerView DictionaryView;
    static ArrayList<String> temp;
    static ArrayList<String> temp2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        DictionaryView = findViewById(R.id.dictionary_view);
        dbhelper = new DBHelper(this);
        temp = CursorHelper();
        temp2 = TranslationArray();
        Adapter adapter = new Adapter(this,temp, temp2);

        DictionaryView.setLayoutManager(new LinearLayoutManager(this));
        DictionaryView.setAdapter(adapter);
        //TODO finish foreground speach
        /*Intent i = new Intent(context, DictionaryActivity.class);
        context.startService(i);

        Notification notification = new Notification();
        startForeground(1, notification);*/

    }
    public ArrayList<String> CursorHelper(){
        SQLiteDatabase database = dbhelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null,
                null, null, null, null);
       ArrayList<String> wordsArray = new ArrayList<>();

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            do {
                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                        ", name = " + cursor.getString(nameIndex));
                wordsArray.add(cursor.getString(nameIndex));
                Toast.makeText(this, cursor.getString(nameIndex), Toast.LENGTH_LONG).show();
            } while (cursor.moveToNext());
        } else
            Log.d("mLog","0 rows");
        dbhelper.close();
        cursor.close();
        return wordsArray;
    }

    public ArrayList<String> TranslationArray(){
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            for (int i = 0; i < CursorHelper().size(); i++) {
                arrayList.add(TranslateYandex(CursorHelper().get(i), "en-ru"));
            }}catch(Exception e){
                e.printStackTrace();
            }
        return arrayList;
    }

    public String TranslateYandex(String textToBeTranslated, String languagePair) {
        YandexTranslate translatorBackgroundTask = new YandexTranslate();
        return translatorBackgroundTask.doInBackground(textToBeTranslated, languagePair);
    }

    public static ArrayList<String> getTemp() {
        return temp;
    }

    public static ArrayList<String> getTemp2() {
        return temp2;
    }
}
