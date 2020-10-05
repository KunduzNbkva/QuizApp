package com.example.quizapp.ui.main;

import androidx.lifecycle.MutableLiveData;

import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.data.remote.QuizApiService;
import com.example.quizapp.models.CategoriesListModel;
import com.example.quizapp.models.QuizModel;

import java.util.List;

public class MainViewModel extends ViewModel {
    public MutableLiveData<Integer> result = new MutableLiveData<>();
    public MutableLiveData<List<QuizModel>> questions = new MutableLiveData<>();
    public MutableLiveData<CategoriesListModel> listCategories = new MutableLiveData<CategoriesListModel>();

    public void plus() {
        if (result.getValue() == null) {
            result.setValue(0);
        }
        result.setValue(result.getValue() + 1);
    }

    public void minus() {
        if (result.getValue() == 0) return;
        if (result.getValue() == null) {
            result.setValue(0);
        }
        result.setValue(result.getValue() - 1);
    }

    public void getCategories() {
        App.quizApiService.getCategories(callbackCategories);
    }

    private QuizApiService.CategoriesCallback callbackCategories = new QuizApiService.CategoriesCallback() {
        @Override
        public void onSuccess(CategoriesListModel categories) {
            listCategories.setValue(categories);
        }

        @Override
        public void onFailure(Throwable exception) {
        }

         };

    private QuizApiService.QuizApiCallback apiCallback=new QuizApiService.QuizApiCallback() {
        @Override
        public void onSuccess(List<QuizModel> quizModel) {
            questions.setValue(quizModel);
        }

        @Override
        public void onFailure(Throwable exception) {

        }
    };
}
