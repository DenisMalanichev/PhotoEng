package com.example.photoeng;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.photoeng.data.DBHelper;

public class Details extends MainScreen {
    private TextView DetailsText;
    private Button BackToDictionaryButton;
    private Button LearnButton;
    private Button DeleteButton;
    private DBHelper mDBHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        DetailsText = findViewById(R.id.details_text);
        BackToDictionaryButton = findViewById(R.id.Back_to_dictionary_button);
        DeleteButton = findViewById(R.id.delete_button);
        mDBHelper = new DBHelper(this);
        Intent intent = getIntent();
        final String title = intent.getStringExtra("title");



        DetailsText.setText(title);

        BackToDictionaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Details.this, MainScreen.class);
                startActivity(intent);
            }
        });
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i =0; i<DictionaryActivity.getTemp().size(); i++){
                    if(DictionaryActivity.getTemp().get(i).equals(title)){
                        DictionaryActivity.getTemp().remove(i);
                        break;
                    }
                }

                SQLiteDatabase database = mDBHelper.getWritableDatabase();
                int deletedItem = database.delete(mDBHelper.TABLE_CONTACTS, mDBHelper.KEY_WORDS+ " = ?", new  String[]{title});
                Toast.makeText(Details.this, "deleted", Toast.LENGTH_SHORT);
                Log.d("DEBAG: deleted item id", ""+deletedItem);
                mDBHelper.close();
                Intent i = new Intent(Details.this, DictionaryActivity.class);
                startActivity(i);
            }
        });
    }

}

