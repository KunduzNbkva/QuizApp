package com.example.quizapp.data;

import com.example.quizapp.data.remote.QuizApiClient;
import com.example.quizapp.data.remote.QuizApiService;
import com.example.quizapp.models.CategoriesListModel;
import com.example.quizapp.models.QuizModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizRepository {
    QuizApiClient apiClient;

    public QuizRepository(QuizApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public void getQuestions(QuizApiClient.QuestionsCallback callback, int amount, String difficulty, int categories) {
        apiClient.getQuestions(new QuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<QuizModel> result) {
                for (QuizModel answer : result) {
                    List<String> answers = new ArrayList<>();
                    answers.add(answer.getCorrectAnswer());
                    answers.addAll(answer.getIncorrectAnswers());
                    Collections.shuffle(answers);
                    answer.setAnswers(answers);
                }
                callback.onSuccess(result);
            }

            @Override
            public void onFailure(Exception exception) {
                callback.onFailure(exception);
            }
        }, amount, difficulty, categories);
    }

    public void getCategories(QuizApiClient.CategoriesCallback callback) {
        apiClient.getCategories(new QuizApiClient.CategoriesCallback() {
            @Override
            public void onSuccess(CategoriesListModel categories) {
                if (categories != null) {
                    callback.onSuccess(categories);
                }
            }

            @Override
            public void onFailure(Exception exception) {

            }
        });
    }
}
