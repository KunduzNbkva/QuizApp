package com.example.quizapp.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    public MutableLiveData<Integer> result=new MutableLiveData<>();

    public void plus(){
        if(result.getValue()==null) {
            result.setValue(0);
        }
       result.setValue(result.getValue()+1);
    }
    public void minus(){
        if (result.getValue()==0)return;
        if(result.getValue()==null) {
        result.setValue(0);
        }
        result.setValue(result.getValue()-1);
    }

}