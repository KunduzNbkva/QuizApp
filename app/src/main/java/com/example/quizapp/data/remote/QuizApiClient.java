package com.example.quizapp.data.remote;

import com.example.quizapp.models.CategoriesListModel;
import com.example.quizapp.models.QuizModel;
import com.example.quizapp.core.BaseCallback;

import java.util.List;

public interface QuizApiClient {
    void getQuestions(QuestionsCallback callback, int amount, String difficulty, int category);

    interface QuestionsCallback extends BaseCallback<List<QuizModel>> {
        @Override
        void onSuccess(List<QuizModel> result);

        @Override
        void onFailure(Exception exception);
    }

    void getCategories(CategoriesCallback callback);

    interface CategoriesCallback {
        void onSuccess(CategoriesListModel categories);

        void onFailure(Exception exception);
    }
}
