package com.example.quizapp.ui.questions;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.data.local.QuestionsDB;
import com.example.quizapp.models.Question;
import java.util.ArrayList;
import java.util.List;

public class QuestionsViewModel extends ViewModel {
    public MutableLiveData<List<Question>> questions = new MutableLiveData<>();
    public MutableLiveData<Integer> amountQuestions = new MutableLiveData<>(); //количество отвеченных вопросов
    private List<Question> list = new ArrayList<>();
    private  QuestionsDB db=new QuestionsDB();

    public QuestionsViewModel() {
        amountQuestions.setValue(0);
    }

    public void getData() {
        list.add(new Question("How old are you?", true, "a", new String[]{"a", "b", "c", "v"}));
        list.add(new Question("How old are ?", true, "b", new String[]{"a", "b", "c", "v"}));
        list.add(new Question("How old ?", true, "c", new String[]{"a", "b", "c", "v"}));
        list.add(new Question("How ?", true, "v", new String[]{"a", "b", "c", "v"}));
        list.add(new Question("H?", true, "a", new String[]{"a", "b", "c", "v"}));


        list.add(new Question("you?", false, "True", new String[]{"False", "True"}));
        list.add(new Question("are you?", false, "False", new String[]{"False", "True"}));
        list.add(new Question("old are you?", false, "True", new String[]{"False", "True"}));
        list.add(new Question("How old are you?", false, "True", new String[]{"False", "True"}));
        list.add(new Question("Are you 12 y.o?", false, "False", new String[]{"False", "True"}));
        questions.setValue(list);
    }


    public void onClick() {
         amountQuestions.setValue(amountQuestions.getValue()+1);
    }
}
