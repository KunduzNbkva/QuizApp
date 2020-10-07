package com.example.quizapp.ui.questions;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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
    private RecyclerView recyclerQuestions;
    private QuestionsAdapter adapter;
    private List<QuizModel> list = new ArrayList<>();
    private Button skip;
    private ActivityQuestionsBinding binding;
    private QuestionsViewModel viewModel;
    private String difficulty;
    private  int amount,category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_questions);
        binding.setViewModel(viewModel);
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
    public void getIntentData(){
        Intent intent = getIntent();
        amount = intent.getIntExtra("amount", 10);
        difficulty = intent.getStringExtra("difficulty");
        category = intent.getIntExtra("category", 9);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void initViews() {
        viewModel = ViewModelProviders.of(this).get(QuestionsViewModel.class);
        recyclerQuestions = findViewById(R.id.recycler_questions);
        list = new ArrayList<>();
        getIntentData();
        viewModel.getQuestions(amount, difficulty, category);
        adapter = new QuestionsAdapter(this, list);
        recyclerQuestions.setAdapter(adapter);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerQuestions);

        viewModel.amountQuestions.observeForever(integer -> {
            recyclerQuestions.smoothScrollToPosition(integer);
        });
        skip = findViewById(R.id.btn_skip);
        skip.setOnClickListener(
                this::onClick
        );
    }

    @Override
    public void onClick(int position) {
        viewModel.onClick();
    }

    private void onClick(View view) {
        viewModel.onClick();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}