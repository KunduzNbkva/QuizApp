package com.example.quizapp.core;

public interface BaseCallback<T> {
    void onSuccess(T result);
    void onFailure(Exception exception);
}
