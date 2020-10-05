package com.example.quizapp;

import android.app.Application;

import com.example.quizapp.data.remote.QuizApiService;

public class App extends Application {
    public static QuizApiService quizApiService;
    @Override
    public void onCreate() {
        super.onCreate();
        quizApiService=new QuizApiService();
    }
}
