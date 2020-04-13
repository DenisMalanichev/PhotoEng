package com.example.photoeng;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class OnlineTranslationForLearning extends Thread {

    private DBHelper dbhelper;
    private Context context;
    public static ArrayList<String> temp;
    public static ArrayList<String> temp2;



    public  OnlineTranslationForLearning(Context context){
        this.context = context;
    }

    @Override
    public void run() {
        dbhelper = new DBHelper(context);
        DictionaryForLearningActivity.setTemp(CursorHelper());
        DictionaryForLearningActivity.setTemp2(TranslationArray());
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
                wordsArray.add(cursor.getString(nameIndex));
            } while (cursor.moveToNext());
        }
        dbhelper.close();
        cursor.close();
        return wordsArray;
    }

    private ArrayList<String> TranslationArray(){
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

