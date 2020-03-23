package com.example.photoeng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Details extends AppCompatActivity {
    TextView DetailsText;
    Button BackToDictionaryButton;
    Button LearnButton;
    Button DeleteButton;
    DBHelper mDBHelper;
    ArrayList<String> learningList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        DetailsText = findViewById(R.id.details_text);
        BackToDictionaryButton = findViewById(R.id.Back_to_dictionary_button);
        DeleteButton = findViewById(R.id.delete_button);
        LearnButton = findViewById(R.id.learn_button_details);
        mDBHelper = new DBHelper(this);
        Intent intent = getIntent();
        final String title = intent.getStringExtra("title");

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
                int deletedItem = database.delete(mDBHelper.TABLE_CONTACTS, mDBHelper.KEY_NAME+ " = ?", new  String[]{title});
                Toast.makeText(Details.this, "deleted", Toast.LENGTH_SHORT);
                Log.d("DEBAG: deleted item id", ""+deletedItem);
                mDBHelper.close();
                Intent i = new Intent(Details.this, DictionaryActivity.class);
                startActivity(i);
            }
        });
        LearnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    learningList.add(title);
            }
        });
        DetailsText.setText(title);
    }
}
