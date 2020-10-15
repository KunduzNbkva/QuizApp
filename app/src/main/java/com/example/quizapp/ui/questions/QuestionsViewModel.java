package com.example.quizapp.ui.questions;
import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.data.remote.QuizApiClient;
import com.example.quizapp.models.QuizModel;

import java.util.List;

public class QuestionsViewModel extends ViewModel {
    private List<QuizModel> mQuestion;
    public MutableLiveData<List<QuizModel>> questions = new MutableLiveData<>();
    public MutableLiveData<Boolean> finishQuiz = new MutableLiveData<>();
    public MutableLiveData<Integer> amountQuestions = new MutableLiveData<>();
    public MutableLiveData<Boolean> resultQuiz = new MutableLiveData<>();


    public QuestionsViewModel() {
        amountQuestions.setValue(0);
    }

    public void getQuestions(int amount, String difficulty, int category) {
        App.quizRepository.getQuestions(new QuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<QuizModel> result) {
                mQuestion = result;
                questions.setValue(result);
            }

            @Override
            public void onFailure(Exception exception) {

            }
        }, amount, difficulty, category);
    }

    public void onClick() {
        if (amountQuestions.getValue() == mQuestion.size() - 1) {
            finishQuiz();
        } else {
            questions.getValue().get(amountQuestions.getValue()).setIsAnswered(false);
            amountQuestions.setValue(amountQuestions.getValue() + 1);
        }
    }

    void onAnswerClick(int pos, int selectedAnswerPosition) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (amountQuestions != null) {
                    if (mQuestion.size() - 1 > pos && pos >= 0) {
                        mQuestion.get(pos).setSelectedAnswerPosition(selectedAnswerPosition);
                        questions.setValue(mQuestion);
                        amountQuestions.setValue(amountQuestions.getValue() + 1);
                    } else if (pos == mQuestion.size() - 1) {
                        resultQuiz.setValue(true);
                    }
                }
            }
        }, 1300);
    }


    public void backPressed() {
        Integer currentPosition = amountQuestions.getValue();
        if (currentPosition != null && currentPosition > 0) {
            amountQuestions.setValue(amountQuestions.getValue() - 1);
        } else {
            finishQuiz();
        }
    }


    private void finishQuiz() {
        finishQuiz.setValue(true);
    }
}



