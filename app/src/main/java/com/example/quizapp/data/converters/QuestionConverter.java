package com.example.quizapp.data.converters;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import com.example.quizapp.models.Question;
import com.example.quizapp.models.QuizModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class QuestionConverter {

    @TypeConverter
    public static String toJsonQuestion(@Nullable List<QuizModel> questions) { //всегда ставить проверку на налл, чтобы не крашилось
        if (questions == null) return null;
        Gson gson = new Gson();
        Type type = new TypeToken<List<QuizModel>>() {}.getType();
        return gson.toJson(questions, type);
    }
    @TypeConverter
    public static List<QuizModel> fromJsonQuestion(@Nullable String json) {
        if(json==null) return null;
        Gson gson=new Gson();
        Type type=new TypeToken<List<QuizModel>>(){}.getType();
        return gson.fromJson(json,type);
    }
}