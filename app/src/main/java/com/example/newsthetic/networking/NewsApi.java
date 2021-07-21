package com.example.newsthetic.networking;

import com.example.newsthetic.model.NewsResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface NewsApi {
  @GET("top-headlines")
  Call<NewsResponse> getNewsList(@QueryMap Map<String, String> parameters);
}
