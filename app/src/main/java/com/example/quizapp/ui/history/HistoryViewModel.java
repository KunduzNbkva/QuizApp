package com.example.quizapp.ui.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.models.ResultModel;

import java.util.List;

public class HistoryViewModel extends ViewModel {
     LiveData<List<ResultModel>> history= App.historyStorage.getAll();

}