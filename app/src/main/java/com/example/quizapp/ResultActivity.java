package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView categoryResult, correctAnswers, totalQuestions, resultPercent,difficulty_txt;
    public String difficulty,category;
    public int amount, correctAns, percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        categoryResult = findViewById(R.id.category_result);
        correctAnswers = findViewById(R.id.correct_answers);
        totalQuestions = findViewById(R.id.total_quiestin);
        resultPercent = findViewById(R.id.result);
        difficulty_txt=findViewById(R.id.result_difficulty);
        getResult();
        setData();
    }

    public void getResult() {
        difficulty = getIntent().getStringExtra("difficulty");
        category = getIntent().getStringExtra("category");
        amount = getIntent().getIntExtra("amount", 10);
        correctAns = getIntent().getIntExtra("amountQuestions", 1); }

    public void setData() {
        categoryResult.setText(category);
        correctAnswers.setText(String.valueOf(correctAns));
        totalQuestions.setText(String.valueOf(amount));
        difficulty_txt.setText(difficulty);
        percent=correctAns*100/amount;
        resultPercent.setText(String.valueOf(percent));
    }
}