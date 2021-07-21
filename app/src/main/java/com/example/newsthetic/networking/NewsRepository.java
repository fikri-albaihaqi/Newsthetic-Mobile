package com.example.newsthetic.networking;

import androidx.lifecycle.MutableLiveData;

import com.example.newsthetic.model.NewsResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
  private static NewsRepository newsRepository;
  private NewsApi newsApi;

  public static NewsRepository getInstance(){
    if (newsRepository == null){
      newsRepository = new NewsRepository();
    }
    return newsRepository;
  }

  public NewsRepository(){
    newsApi = RetrofitService.createService(NewsApi.class);
  }

  public MutableLiveData<NewsResponse> getNews(Map<String, String> source){
    MutableLiveData<NewsResponse> newsData = new MutableLiveData<>();
    newsApi.getNewsList(source).enqueue(new Callback<NewsResponse>() {
      @Override
      public void onResponse(Call<NewsResponse> call,
                             Response<NewsResponse> response) {
        if (response.isSuccessful()){
          newsData.setValue(response.body());
        }
      }

      @Override
      public void onFailure(Call<NewsResponse> call, Throwable t) {
        newsData.setValue(null);
      }
    });
    return newsData;
  }

//  public MutableLiveData<NewsResponse> getNewsBySearch(String search, String key){
//    MutableLiveData<NewsResponse> newsData = new MutableLiveData<>();
//    newsApi.getNewsSearch(search, key).enqueue(new Callback<NewsResponse>() {
//      @Override
//      public void onResponse(Call<NewsResponse> call,
//                             Response<NewsResponse> response) {
//        if (response.isSuccessful()){
//          newsData.setValue(response.body());
//        }
//      }
//
//      @Override
//      public void onFailure(Call<NewsResponse> call, Throwable t) {
//        newsData.setValue(null);
//      }
//    });
//    return newsData;
//  }
}
