package com.example.quizapp.data.converters;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import com.example.quizapp.models.Question;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Long toJsonQuestion(@Nullable Date date) { //всегда ставить проверку на налл, чтобы не крашилось
        if (date == null) return null;

        return date.getTime();// Показывает в миллиссекундах с 1970 года
    }

    @TypeConverter
    public static Date fromJsonQuestion(@Nullable Long timeStamp) {
        if (timeStamp == null) return null;
        return new Date(timeStamp);
    }
}