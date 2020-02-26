package com.example.photoeng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DictionaryActivity extends AppCompatActivity {

    private String s1[], s2[];
    DBHelper dbhelper;
    private androidx.recyclerview.widget.RecyclerView DictionaryView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        Cursor cursor;
        DictionaryView = findViewById(R.id.dictionary_view);
        dbhelper = new DBHelper(this);
        //SQLiteDatabase database;
        ArrayList<String> temp = CursorHelper();
        Log.i("CursorHelper", temp.size()+"");
        //s1= new String[4];
        /*for(int i =0; i<CursorHelper().size(); i++){


        }*/
        //CursorHelper().toArray(s1);
//        for(int q = 0; q<TranslationArray().size(); q++){
//            s2[q] = TranslationArray().get(q);
//        }
        //Adapter adapter = new Adapter(this, s1, s2);
        Adapter adapter = new Adapter(this,temp);

        DictionaryView.setLayoutManager(new LinearLayoutManager(this));
        DictionaryView.setAdapter(adapter);

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
       // database.delete(DBHelper.TABLE_CONTACTS, null, null);
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

}
