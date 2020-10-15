package com.example.quizapp.ui.settings;

import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;

public class SettingsViewModel extends ViewModel {
    void deleteAll(){
        App.historyStorage.deleteAll();
    }
}