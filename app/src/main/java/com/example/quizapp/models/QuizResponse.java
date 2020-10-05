package com.example.quizapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuizResponse {
    @SerializedName("response_code")
    @Expose
    private String responseCode;
    @SerializedName("results")
    @Expose
    private List<QuizModel> results;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public List<QuizModel> getResults() {
        return results;
    }

    public void setResults(List<QuizModel> results) {
        this.results = results;
    }
}
