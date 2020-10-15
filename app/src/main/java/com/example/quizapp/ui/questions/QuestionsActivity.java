package com.example.quizapp.ui.questions;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.ResultActivity;
import com.example.quizapp.adapter.QuestionsAdapter;
import com.example.quizapp.databinding.ActivityQuestionsBinding;
import com.example.quizapp.models.QuizModel;
import com.example.quizapp.models.ResultModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity implements OnItemClickListener {
    private RecyclerView recyclerQuestions;
    private Toolbar toolbar;
    private QuestionsAdapter adapter;
    private List<QuizModel> list = new ArrayList<>();
    private Button skip;
    private ActivityQuestionsBinding binding;
    private QuestionsViewModel viewModel;
    private String difficulty,categoryResult;
    private int amount, category;
    private int correctAnswers;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_questions);
        binding.setViewModel(viewModel);
        initViews();
        observeQuestions();
    }


    @SuppressLint("ClickableViewAccessibility")
    public void initViews() {
        createToolbar();
        viewModel = ViewModelProviders.of(this).get(QuestionsViewModel.class);
        recyclerQuestions = findViewById(R.id.recycler_questions);
        disableSliding(recyclerQuestions);
        progressBar=findViewById(R.id.questions_progress);
        list = new ArrayList<>();
        getIntentData();
        viewModel.getQuestions(amount, difficulty, category);
        adapter = new QuestionsAdapter(this, list);
        recyclerQuestions.setAdapter(adapter);
        observeAmountQuestions();
        observerResult();
        skip = findViewById(R.id.btn_skip);
        skip.setOnClickListener(
                this::onClick
        );
        observeFinish();

    }

    private void observeQuestions() {
        viewModel.questions.observe(this,
                new Observer<List<QuizModel>>() {
                    @Override
                    public void onChanged(List<QuizModel> response) {
                        list.addAll(response);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    public void observeAmountQuestions() {
        viewModel.amountQuestions.observeForever(integer -> {
            recyclerQuestions.scrollToPosition(integer);
            progressBar.setProgress(integer);
        });
    }

    private void observerResult() {
        viewModel.resultQuiz.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Intent resultIntent=new Intent(QuestionsActivity.this,ResultActivity.class);
                    resultIntent.putExtra("difficulty",getIntent().getStringExtra("difficulty"));
                    resultIntent.putExtra("category",categoryResult);
                    resultIntent.putExtra("amount",amount);
                    resultIntent.putExtra("amountQuestions",correctAnswers);
                    ResultModel resultModel=new ResultModel(0,categoryResult,difficulty,correctAnswers,list,new Date());
                    App.historyStorage.saveQuizResult(resultModel); //saving
                    startActivity(resultIntent);
                }
            }
        });
    }

    private void observeFinish() {
        viewModel.finishQuiz.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                finish();
            }
        });
    }

    public void getIntentData() {
        Intent intent = getIntent();
        amount = intent.getIntExtra("amount", 10);
        progressBar.setMax(amount);
        difficulty = intent.getStringExtra("difficulty");
        category = intent.getIntExtra("category", 9);
        categoryResult=intent.getStringExtra("categoryResult");
    }

    public  void createToolbar(){
        toolbar = findViewById(R.id.questions_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void disableSliding(RecyclerView rv) {
        rv.setOnTouchListener((v, event) -> true);
    }

    private void onClick(View view) {
        viewModel.onClick();
    }

    @Override
    public void onBackPressed() {
        viewModel.backPressed();
        observeAmountQuestions();
    }

    @Override
    public void onClick(int position, int selectedAnswerPosition) {
        viewModel.onAnswerClick(position, selectedAnswerPosition);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            viewModel.backPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void isAnswered(Boolean b) {
        if(b){
            correctAnswers++;
        }
        Log.e("correct","answers"+correctAnswers);
    }


}