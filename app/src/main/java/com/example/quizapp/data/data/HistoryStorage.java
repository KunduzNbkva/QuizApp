package com.example.quizapp.data.data;

import androidx.lifecycle.LiveData;

import com.example.quizapp.data.local.QuestionDao;
import com.example.quizapp.models.Question;
import com.example.quizapp.models.ResultModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryStorage implements IHistoryStorage {
    private QuestionDao dao;

    public HistoryStorage(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public LiveData<List<ResultModel>> getAll() {
        return dao.getAll();
    }

    @Override
    public Question getQuizResult(int id) {
        return null;
    }

    @Override
    public int saveQuizResult(ResultModel result) {
        return (int)dao.insert(result);
    }

    @Override
    public void delete(int id) {
         dao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }


}
