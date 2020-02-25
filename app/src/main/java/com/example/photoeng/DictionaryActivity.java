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
    Context mContext;
    DBHelper dbhelper;
    private DBHelper db;
    private androidx.recyclerview.widget.RecyclerView DictionaryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        Cursor cursor;
        DictionaryView = findViewById(R.id.dictionary_view);
        for(int i =0; i<CursorHelper().size(); i++){
            s1[i] = CursorHelper().get(i);
        }
        s2 = getResources().getStringArray(R.array.translation);
        Adapter adapter = new Adapter(this, s1, s2);
        DictionaryView.setAdapter(adapter);
        DictionaryView.setLayoutManager(new LinearLayoutManager(this));

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
                wordsArray.add(cursor.getString(idIndex));
            } while (cursor.moveToNext());
        } else
            Log.d("mLog","0 rows");
        database.delete(DBHelper.TABLE_CONTACTS, null, null);
        dbhelper.close();
        cursor.close();
        return wordsArray;
    }

}
