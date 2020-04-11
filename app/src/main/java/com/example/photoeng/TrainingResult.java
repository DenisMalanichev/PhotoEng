package com.example.photoeng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TrainingResult extends AppCompatActivity {
    private Button ResultBackButton;
    private Button ResultAgainButton;
    private TextView ResultTextView;
    private int trueAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_result);

        ResultAgainButton = findViewById(R.id.result_button_again);
        ResultBackButton = findViewById(R.id.result_button_back);
        ResultTextView = findViewById(R.id.result_text_view);

        Intent intent = new Intent(TrainingResult.this, TrainingActivity.class);
        trueAnswers = intent.getIntExtra("ExtraTrueAnswers", 0);

        ResultTextView.setText("Правельных ответов - " + trueAnswers);
        ResultBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(TrainingResult.this, MainScreen.class);
                startActivity(intent1);
            }
        });
        ResultAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(TrainingResult.this, TrainingActivity.class);
                startActivity(intent1);
            }
        });
    }
}
