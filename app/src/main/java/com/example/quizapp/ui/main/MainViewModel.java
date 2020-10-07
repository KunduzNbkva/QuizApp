package com.example.quizapp.ui.main;

import androidx.lifecycle.MutableLiveData;

import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.data.remote.QuizApiClient;
import com.example.quizapp.data.remote.QuizApiService;
import com.example.quizapp.models.CategoriesListModel;
import com.example.quizapp.models.QuizModel;

import java.util.List;

public class MainViewModel extends ViewModel {
    public MutableLiveData<Integer> result = new MutableLiveData<>();
    public MutableLiveData<CategoriesListModel> listCategories = new MutableLiveData<>();


    public void getCategories() {
        App.quizApiService.getCategories(new QuizApiClient.CategoriesCallback() {
            @Override
            public void onSuccess(CategoriesListModel categories) {
                listCategories.setValue(categories);
            }

            @Override
            public void onFailure(Exception exception) {

            }
        });
    }
}
