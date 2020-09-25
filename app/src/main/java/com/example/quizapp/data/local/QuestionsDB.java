package com.example.quizapp.data.local;

import com.example.quizapp.models.Question;

import java.util.List;

public class QuestionsDB {

    List<Question> list;

    public QuestionsDB(List<Question> list) {
        this.list = list;
    }

    public QuestionsDB() {

    }

    public List<Question> getList(){
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
        return list;
    }

    public void getQuestion(){
        list.get(0);
    }
}
