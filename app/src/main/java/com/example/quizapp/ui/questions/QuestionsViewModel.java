package com.example.quizapp.ui.questions;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.data.remote.QuizApiService;
import com.example.quizapp.models.QuizModel;

import java.util.List;

public class QuestionsViewModel extends ViewModel {
    public MutableLiveData<List<QuizModel>> questions = new MutableLiveData<>();
    public MutableLiveData<Integer> amountQuestions = new MutableLiveData<>(); //количество отвеченных вопросов


    public QuestionsViewModel() {
        amountQuestions.setValue(0);
    }

    public void getData(int amount,String difficulty,int category) {
        App.quizApiService.getQuestions(
                callbackData,
                amount,
                difficulty,
                category
        );

    }
    private QuizApiService.QuizApiCallback callbackData = new QuizApiService.QuizApiCallback() {

        @Override
        public void onSuccess(List<QuizModel> quizModel) {
            questions.setValue(quizModel);
        }

        @Override
        public void onFailure(Throwable exception) {

        }
    };

        public void onClick() {
            amountQuestions.setValue(amountQuestions.getValue() + 1);
        }


}



