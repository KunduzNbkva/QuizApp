package com.example.quizapp.adapter;

import android.annotation.SuppressLint;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.models.QuizModel;
import com.example.quizapp.models.Type;
import com.example.quizapp.ui.questions.OnItemClickListener;

public class ViewHolder extends RecyclerView.ViewHolder {
    QuizModel quizModel;
    TextView questionTextView;
    Button answer1, answer2, answer3, answer4, boolean_answer1, boolean_answer2;
    LinearLayout multiplyButtons, booleanButtons;
    private int position;
    private String correctAnswer;


    public ViewHolder(@NonNull View itemView, OnItemClickListener itemClickListener) {
        super(itemView);
        questionTextView = itemView.findViewById(R.id.question_txt);
        multiplyButtons = itemView.findViewById(R.id.multiply_buttons);
        booleanButtons = itemView.findViewById(R.id.boolean_buttons);
        answer1 = itemView.findViewById(R.id.answer1);
        answer2 = itemView.findViewById(R.id.answer2);
        answer3 = itemView.findViewById(R.id.answer3);
        answer4 = itemView.findViewById(R.id.answer4);
        answer1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                itemClickListener.onClick(getAdapterPosition(), 1);
                answer1.setBackgroundColor(R.color.blue);
                onAnswerClick(answer1, answer1.getText().toString(), correctAnswer,itemClickListener);
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                itemClickListener.onClick(getAdapterPosition(), 2);
                answer2.setBackgroundColor(R.color.colorAccent);
                onAnswerClick(answer2, answer2.getText().toString(), correctAnswer,itemClickListener);
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                itemClickListener.onClick(getAdapterPosition(), 3);
                answer3.setBackgroundColor(R.color.colorPrimary);
                onAnswerClick(answer3, answer3.getText().toString(), correctAnswer,itemClickListener);
            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                itemClickListener.onClick(getAdapterPosition(), 4);
                answer4.setBackgroundColor(R.color.colorPrimaryDark);
                onAnswerClick(answer4, answer4.getText().toString(), correctAnswer,itemClickListener);
            }
        });
        boolean_answer1 = itemView.findViewById(R.id.boolean_answer1);
        boolean_answer2 = itemView.findViewById(R.id.boolean_answer2);
        boolean_answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onClick(getAdapterPosition(), 1);
                onAnswerClick(boolean_answer1, boolean_answer1.getText().toString(), correctAnswer,itemClickListener);
            }
        });
        boolean_answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onClick(getAdapterPosition(), 2);
                onAnswerClick(boolean_answer2, boolean_answer2.getText().toString(), correctAnswer,itemClickListener);
            }
        });
    }

    public void onBind(QuizModel quizModel, int position) {
        this.quizModel = quizModel;
        setClickable(quizModel.getIsAnswered());
        clearHolder();
        this.position = position;
        questionTextView.setText(Html.fromHtml(quizModel.getQuestion()));
        Log.e("Type", "type is " + quizModel.getType());
        if (quizModel.getType() == Type.MULTIPLE) {
            multiplyButtons.setVisibility(View.VISIBLE);
            booleanButtons.setVisibility(View.GONE);
            answer1.setText(Html.fromHtml(quizModel.getAnswers().get(0)));
            answer2.setText(Html.fromHtml(quizModel.getAnswers().get(1)));
            answer3.setText(Html.fromHtml(quizModel.getAnswers().get(2)));
            answer4.setText(Html.fromHtml(quizModel.getAnswers().get(3)));
        } else {
            multiplyButtons.setVisibility(View.GONE);
            booleanButtons.setVisibility(View.VISIBLE);
            boolean_answer1.setText(Html.fromHtml(quizModel.getAnswers().get(0)));
            boolean_answer2.setText(Html.fromHtml(quizModel.getAnswers().get(1)));
        }
        correctAnswer = quizModel.getCorrectAnswer();
    }

    public void onAnswerClick(View view, String selected, String correctAnswer,OnItemClickListener listener) {
        quizModel.setIsAnswered(true);
        setClickable(false);
        if (selected.equals(correctAnswer)) {
            view.setBackgroundResource(R.drawable.btn_background_correct);
            listener.isAnswered(true);
        } else {
            view.setBackgroundResource(R.drawable.btn_background_wrong);
            listener.isAnswered(false);
        }

    }


    private void clearHolder() {
        answer1.setBackgroundResource(R.drawable.btn_default_background);
        answer2.setBackgroundResource(R.drawable.btn_default_background);
        answer3.setBackgroundResource(R.drawable.btn_default_background);
        answer4.setBackgroundResource(R.drawable.btn_default_background);
        boolean_answer1.setBackgroundResource(R.drawable.btn_default_background);
        boolean_answer2.setBackgroundResource(R.drawable.btn_default_background);
    }

    private void setClickable(Boolean bool) {
        answer1.setClickable(bool);
        answer2.setClickable(bool);
        answer3.setClickable(bool);
        answer4.setClickable(bool);
        boolean_answer1.setClickable(bool);
        boolean_answer2.setClickable(bool);
    }

//    private void observeClick(){
//        quizModel.getIsAnswered().observeForever(new Observer<Boolean>() {
//            @Override
//            public void onChanged(Boolean aBoolean) {
//                if(aBoolean){
//                setClickable(false);
//                }
//            }
//        });
//    }
}


