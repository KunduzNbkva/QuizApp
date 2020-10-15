package com.example.quizapp.data.data;

import androidx.lifecycle.LiveData;

import com.example.quizapp.models.Question;
import com.example.quizapp.models.ResultModel;

import java.util.List;


public interface IHistoryStorage {
    LiveData<List<ResultModel>>getAll();
    Question getQuizResult(int id);

    int saveQuizResult(ResultModel result);

    void delete(int id);

    void deleteAll();
}
