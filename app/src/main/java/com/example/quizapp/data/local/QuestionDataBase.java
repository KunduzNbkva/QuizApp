package com.example.quizapp.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.quizapp.models.ResultModel;

@Database(entities = {ResultModel.class},version = 3)
public abstract class QuestionDataBase extends RoomDatabase {

    public abstract QuestionDao questionDao();
}
