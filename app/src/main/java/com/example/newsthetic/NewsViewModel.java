package com.example.newsthetic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsthetic.model.NewsResponse;
import com.example.newsthetic.networking.NewsRepository;

public class NewsViewModel extends ViewModel {
  private MutableLiveData<NewsResponse> mutableLiveData;
  private NewsRepository newsRepository;

  public void init(){
    if (mutableLiveData != null){
      return;
    }
    newsRepository = NewsRepository.getInstance();
    mutableLiveData = newsRepository.getNews("id", "f7aa06702b2844f19df37a220907cfc2");
  }

  public LiveData<NewsResponse> getNewsRepository() {
    return mutableLiveData;
  }
}
