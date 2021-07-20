package com.example.newsthetic.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
  @GET("top-headlines")
  Call<List<Article>> getArticles();
}
