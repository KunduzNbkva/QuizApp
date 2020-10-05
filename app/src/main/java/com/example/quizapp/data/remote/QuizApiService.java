package com.example.quizapp.data.remote;

import android.util.Log;

import com.example.quizapp.models.CategoriesListModel;
import com.example.quizapp.models.QuizModel;
import com.example.quizapp.models.QuizResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizApiService {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    QuizeApi service = retrofit.create(QuizeApi.class);

    public void getQuestions(final QuizApiCallback callback, int amount,String difficulty,int category) {
        service.getQuestions( amount, difficulty,category).enqueue(new Callback<QuizResponse>() {

            @Override
            public void onResponse(Call<QuizResponse> call, Response<QuizResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess( response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<QuizResponse> call, Throwable t) {
                callback.onFailure(t);
                Log.e("onFailure", "Failure is ", t);
            }
        });


    }
   public void getCategories(CategoriesCallback callback){
        Call <CategoriesListModel> call=service.getCategories();
        call.enqueue(new Callback<CategoriesListModel>() {
            @Override
            public void onResponse(Call<CategoriesListModel> call, Response<CategoriesListModel> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<CategoriesListModel> call, Throwable t) {

            }
        });

   }

    interface QuizeApi {
        @GET("api.php")
        Call<QuizResponse> getQuestions(@Query("amount") int amount,
                                        @Query("difficulty") String difficulty,
                                        @Query("category") int category);
        @GET("api_category.php")
        Call<CategoriesListModel> getCategories();

    }

    public interface QuizApiCallback {
        void onSuccess(List<QuizModel> quizModel);

        void onFailure(Throwable exception);
    }
    public interface CategoriesCallback{
        void onSuccess(CategoriesListModel categories);
        void onFailure(Throwable exception);
    }
}
