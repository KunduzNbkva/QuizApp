package com.example.quizapp.ui.questions;

public interface OnItemClickListener {
    void onClick(int position,int selectedAnswerPosition);

    void isAnswered(Boolean b);

}
