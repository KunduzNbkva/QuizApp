package com.example.quizapp.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.quizapp.models.ResultModel;

import java.util.List;

@Dao
public interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ResultModel resultModel);

    @Query("SELECT * FROM resultmodel WHERE id=:id")      //получение данных по айди и удаление так же по айди
    ResultModel getById(int id);

    @Query("DELETE FROM resultmodel WHERE id= :id")
    void deleteById(int id);

    @Query("SELECT*FROM resultmodel")
    LiveData<List<ResultModel>> getAll();

    @Query("DELETE FROM resultmodel")
    void deleteAll();

}
