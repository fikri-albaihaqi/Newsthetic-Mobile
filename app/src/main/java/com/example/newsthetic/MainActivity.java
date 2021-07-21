package com.example.newsthetic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.newsthetic.model.NewsArticle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  ArrayList<NewsArticle> articleArrayList = new ArrayList<>();
  NewsAdapter newsAdapter;
  RecyclerView recyclerView;
  NewsViewModel newsViewModel;
  EditText search;
  String country;
  Button cari;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    recyclerView = findViewById(R.id.list_berita);
    search = findViewById(R.id.search);
    cari = findViewById(R.id.cari);
    country = "id";
    newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);

//    if (country == "id") {
//      newsViewModel.init();
//      loadNews(country);
//    }


//    search.setOnEditorActionListener(new TextView.OnEditorActionListener(){
//
//      @Override
//      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//        if (actionId == EditorInfo.IME_ACTION_SEND) {
//          loadSearchResult(search.getText().toString());
//          search.setText("");
//          return true;
//        }else{
//          return false;
//        }
//      }
//    });

    cari.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        newsViewModel.init();
        loadSearchResult(search.getText().toString());
        search.setText("");
      }
    });
  }

  public void loadNews(String country) {
    newsViewModel.getNews(country).observe(this, newsResponse -> {
      List<NewsArticle> newsArticles = newsResponse.getArticles();
      articleArrayList.clear();
      articleArrayList.addAll(newsArticles);
      newsAdapter.notifyDataSetChanged();
    });
    setupRecyclerView();
  }

  public void loadSearchResult(String search) {
//    newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
    newsViewModel.getNewsBySearch(search).observe(this, newsResponse -> {
      List<NewsArticle> newsArticles = newsResponse.getArticles();
      articleArrayList.clear();
      articleArrayList.addAll(newsArticles);
      newsAdapter.notifyDataSetChanged();
    });
    setupRecyclerView();
  }

  private void setupRecyclerView() {
    if (newsAdapter == null) {
      newsAdapter = new NewsAdapter(MainActivity.this, articleArrayList, new NewsAdapter.NewsDiff());
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      recyclerView.setAdapter(newsAdapter);
      recyclerView.setItemAnimator(new DefaultItemAnimator());
    } else {
      newsAdapter.notifyDataSetChanged();
    }
  }

  public void detailBerita(View view) {
//    if (view.getId() == R.id.cardHeadline) {
    Intent intent = new Intent(this, DetailBeritaActivity.class);
    startActivity(intent);
  }
}