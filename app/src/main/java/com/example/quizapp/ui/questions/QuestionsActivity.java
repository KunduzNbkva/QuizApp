package com.example.quizapp.ui.questions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;

import com.example.quizapp.R;
import com.example.quizapp.adapter.QuestionsAdapter;
import com.example.quizapp.data.local.QuestionsDB;
import com.example.quizapp.databinding.ActivityQuestionsBinding;
import com.example.quizapp.models.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity implements OnItemClickListener{
  RecyclerView recyclerQuestions;
  QuestionsAdapter adapter;
  List<Question> list;
  Button skip;
  RecyclerView.LayoutManager layoutManager;
  ActivityQuestionsBinding binding;
  QuestionsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        initViews();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void initViews() {
        viewModel = ViewModelProviders.of(this).get(QuestionsViewModel.class);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_questions);
        binding.setViewModel(viewModel);
        list = new ArrayList<Question>();
        viewModel.getData();
        viewModel.questions.observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                list.addAll(questions);
                adapter.notifyDataSetChanged();
            }
        });
        recyclerQuestions = findViewById(R.id.recycler_questions);
        adapter = new QuestionsAdapter(list,this);
        layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerQuestions.setLayoutManager(layoutManager);
        recyclerQuestions.setAdapter(adapter);
        recyclerQuestions.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        skip = findViewById(R.id.btn_skip);
        skip.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewModel.onClick();
                        Log.e("log","click is working");
                    }
                }
        );
        viewModel.amountQuestions.observeForever(new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                recyclerQuestions.smoothScrollToPosition(integer);
                Log.e("log"," is working " + integer);

            }
        });
    }

    @Override
    public void onClick(int position) {
        viewModel.onClick(); //
    }
}