package com.example.quizapp;

import android.app.Application;

import androidx.room.Room;

import com.example.quizapp.data.QuizRepository;
import com.example.quizapp.data.data.HistoryStorage;
import com.example.quizapp.data.data.IHistoryStorage;
import com.example.quizapp.data.local.QuestionDataBase;
import com.example.quizapp.data.pref.SharedPref;
import com.example.quizapp.data.remote.QuizApiService;

public class App extends Application {
    public static QuizApiService quizApiService;
    public static QuizRepository quizRepository;
    public static QuestionDataBase database;
    public static IHistoryStorage historyStorage;
    public static SharedPref prefs;


    @Override
    public void onCreate() {
        super.onCreate();
        quizApiService=new QuizApiService();
        quizRepository=new QuizRepository(quizApiService);
        database= Room.databaseBuilder(getApplicationContext(),
                QuestionDataBase.class,
                "questionDataBase")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries() //запускаем в главном потоке, и игнорируем его предупрежления
                .build();
        database.questionDao();
        historyStorage=new HistoryStorage(database.questionDao());
        prefs=new SharedPref(this);
    }
    public SharedPref getPrefs(){
        return prefs;
    }
}
