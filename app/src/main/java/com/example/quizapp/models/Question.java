package com.example.quizapp.models;


public class Question {
    String question;
    Boolean multiply;
    String correctAnswer;
    String[] answers;

    public Question(String question, Boolean multiply,  String correctAnswer, String[] answers) {
        this.question = question;
        this.multiply = multiply;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Boolean getMultiply() {
        return multiply;
    }

    public void setMultiply(Boolean multiply) {
        this.multiply = multiply;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }


}