package com.example.quizapp.data.remote;

import com.example.quizapp.models.CategoriesListModel;
import com.example.quizapp.models.QuizResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizApiService implements QuizApiClient {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    QuizeApi service = retrofit.create(QuizeApi.class);


    @Override
    public void getQuestions(QuestionsCallback callback, int amount, String difficulty, int category) {
        service.getQuestions(amount, difficulty, category).enqueue(new Callback<QuizResponse>() {
            @Override
            public void onResponse(Call<QuizResponse> call, Response<QuizResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getResults());
                } else {
                    callback.onFailure(new Exception("Response is empty: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<QuizResponse> call, Throwable t) {
                callback.onFailure(new Exception("Response is empty: " + t.getMessage()));
            }
        });
    }

    @Override
    public void getCategories(QuizApiClient.CategoriesCallback callback) {
        service.getCategories().enqueue(new Callback<CategoriesListModel>() {
            @Override
            public void onResponse(Call<CategoriesListModel> call, Response<CategoriesListModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Exception("Response is empty: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<CategoriesListModel> call, Throwable t) {
                callback.onFailure(new Exception("Response is empty: " + t.getMessage()));
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
}
