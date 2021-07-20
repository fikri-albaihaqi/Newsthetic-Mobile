package com.example.newsthetic.networking;

import com.example.newsthetic.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
  @GET("top-headlines")
  Call<NewsResponse> getNewsList(@Query("country") String newsCountry, @Query("apiKey") String apiKey);
}
