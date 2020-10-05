package com.example.quizapp.ui.questions;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.quizapp.R;
import com.example.quizapp.adapter.QuestionsAdapter;
import com.example.quizapp.databinding.ActivityQuestionsBinding;
import com.example.quizapp.models.QuizModel;

import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity implements OnItemClickListener {
  RecyclerView recyclerQuestions;
  QuestionsAdapter adapter;
  List<QuizModel> list = new ArrayList<>();
  Button skip;
  ActivityQuestionsBinding binding;
  QuestionsViewModel viewModel;
  private String difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        initViews();
        observeQuestions();
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

    @SuppressLint("ClickableViewAccessibility")
    public void initViews() {
        viewModel = ViewModelProviders.of(this).get(QuestionsViewModel.class);
        Intent intent=getIntent();
        int amount=intent.getIntExtra("amount",10);
        difficulty=intent.getStringExtra("difficulty");
        int category=intent.getIntExtra("category",9);
        viewModel.getData(amount,difficulty,category);
        Log.e("difficulty"," "+ difficulty);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_questions);
        binding.setViewModel(viewModel);
        list = new ArrayList<>();
        recyclerQuestions = findViewById(R.id.recycler_questions);
        adapter=new QuestionsAdapter(this, list);
        recyclerQuestions.setAdapter(adapter);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerQuestions);

        viewModel.amountQuestions.observeForever(integer -> {
            recyclerQuestions.smoothScrollToPosition(integer);
            Log.e("log"," is working " + integer);

        });
        skip = findViewById(R.id.btn_skip);
        skip.setOnClickListener(
                this::onClick
        );
    }



    @Override
    public void onClick(int position) {
        viewModel.onClick();
        Log.e("log", "click is working");
    }

    private void onClick(View view) {
        viewModel.onClick();
        Log.e("log", "click is working");
    }

}