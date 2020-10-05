package com.example.quizapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesListModel  {
        @SerializedName("trivia_categories")
        @Expose
        private List<TriviaCategoryModel> triviaCategories;

        public List<TriviaCategoryModel> getTriviaCategories() {
            return triviaCategories;
        }

        public void setTriviaCategories(List<TriviaCategoryModel> triviaCategories) {
            this.triviaCategories = triviaCategories;
        }

    }
