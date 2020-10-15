package com.example.quizapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.models.Question;
import com.example.quizapp.models.ResultModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity {
    private TextView categoryResult, correctAnswers, totalQuestions, resultPercent,difficulty_txt;
    public String difficulty,category;
    public int amount, correctAns, percent;
    private Button finish;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        finish=findViewById(R.id.finish_btn);
        categoryResult = findViewById(R.id.category_result);
        correctAnswers = findViewById(R.id.correct_answers);
        totalQuestions = findViewById(R.id.total_quiestion);
        resultPercent = findViewById(R.id.result);
        difficulty_txt=findViewById(R.id.result_difficulty);
        finish.setOnClickListener(finishClickListener);
        getResult();
        setData();

    }

    @SuppressLint("NewApi")
    public void getResult() {
        difficulty = getIntent().getStringExtra("difficulty");
        category = getIntent().getStringExtra("category");
        amount = getIntent().getIntExtra("amount", 10);
        correctAns = getIntent().getIntExtra("amountQuestions", 1);
    }

    public void setData() {
        categoryResult.setText(category);
        correctAnswers.setText(String.valueOf(correctAns));
        totalQuestions.setText(String.valueOf(amount));
        difficulty_txt.setText(difficulty);
        percent=correctAns*100/amount;
        resultPercent.setText(String.valueOf(percent));
    }

    View.OnClickListener finishClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(ResultActivity.this,MainActivity.class));
        }
    };
}