package com.example.photoeng.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contactDb";
    public static final String TABLE_CONTACTS = "contacts";

    public static final String KEY_ID = "_id";
    public static final String KEY_WORDS = "words";
    public static final String KEY_TRANSLATION = "translation";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CONTACTS + "(" + KEY_ID
                + " integer primary key," + KEY_WORDS + " text," + KEY_TRANSLATION + " text" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);

        onCreate(db);

    }

    public void addWordsToDB(String name, String word){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values= new ContentValues();

        values.put(KEY_WORDS, name);
        values.put(KEY_TRANSLATION, word);
        db.insert(TABLE_CONTACTS, null, values);
    }
}
