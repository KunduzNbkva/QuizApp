package com.example.quizapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.example.quizapp.data.converters.DateConverter;
import com.example.quizapp.data.converters.QuestionConverter;
import java.util.Date;
import java.util.List;

@Entity()
public class ResultModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "difficulty")
    private String difficulty;
    @ColumnInfo(name = "correctAnswer")
    private int correctAnswer;
    @TypeConverters({QuestionConverter.class})
    private List<QuizModel> questions;
    @TypeConverters({DateConverter.class})
    private Date date;

    public ResultModel(int id,String category, String difficulty, int correctAnswer,List<QuizModel> questions, Date date) {
        this.id=id;
        this.category = category;
        this.difficulty = difficulty;
        this.correctAnswer = correctAnswer;
        this.questions = questions;
        this.date = date;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<QuizModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuizModel> questions) {
        this.questions = questions;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


