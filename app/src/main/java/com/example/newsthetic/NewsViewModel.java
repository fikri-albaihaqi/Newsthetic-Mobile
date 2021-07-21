package com.example.newsthetic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsthetic.model.NewsResponse;
import com.example.newsthetic.networking.NewsRepository;

import java.util.HashMap;
import java.util.Map;

public class NewsViewModel extends ViewModel {
  private MutableLiveData<NewsResponse> mutableLiveData;
  private NewsRepository newsRepository;
  private Map<String, String> parameters;

  public NewsViewModel() {
    parameters = new HashMap<>();
    newsRepository = new NewsRepository();
  }

  public void init(){
    if (mutableLiveData != null){
      return;
    }
    parameters.clear();
    newsRepository = NewsRepository.getInstance();
    parameters.put("apiKey", "f7aa06702b2844f19df37a220907cfc2");
//    mutableLiveData = newsRepository.getNews(parameter, "f7aa06702b2844f19df37a220907cfc2");
  }

  public LiveData<NewsResponse> getNews(String country) {
    if (mutableLiveData == null) {
      parameters.put("country", country);
      mutableLiveData = newsRepository.getNews(parameters);
//      mutableLiveData = newsRepository.getNews(country, "f7aa06702b2844f19df37a220907cfc2");
    }
    return mutableLiveData;
  }

  public LiveData<NewsResponse> getNewsBySearch(String search) {
    if (mutableLiveData == null) {
      parameters.put("q", search);
      mutableLiveData = newsRepository.getNews(parameters);
    }
    return mutableLiveData;
  }

//  public LiveData<NewsResponse> getNewsBySearch(String search) {
//    if (mutableLiveData == null) {
//      mutableLiveData = newsRepository.getNewsBySearch(search, "f7aa06702b2844f19df37a220907cfc2");
//    }
//    return mutableLiveData;
//  }

  public LiveData<NewsResponse> getNewsRepository() {
    return mutableLiveData;
  }
}
