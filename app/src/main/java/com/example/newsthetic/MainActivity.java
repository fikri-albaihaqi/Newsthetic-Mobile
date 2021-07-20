package com.example.newsthetic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.newsthetic.model.NewsAdapter;
import com.example.newsthetic.model.NewsArticle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  ArrayList<NewsArticle> articleArrayList = new ArrayList<>();
  NewsAdapter newsAdapter;
  RecyclerView recyclerView;
  NewsViewModel newsViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    recyclerView = findViewById(R.id.list_berita);

    newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
    newsViewModel.init();
    newsViewModel.getNewsRepository().observe(this, newsResponse -> {
      List<NewsArticle> newsArticles = newsResponse.getArticles();
      articleArrayList.addAll(newsArticles);
      newsAdapter.notifyDataSetChanged();
    });

    setupRecyclerView();
  }

  private void setupRecyclerView() {
    if (newsAdapter == null) {
      newsAdapter = new NewsAdapter(MainActivity.this, articleArrayList);
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      recyclerView.setAdapter(newsAdapter);
      recyclerView.setItemAnimator(new DefaultItemAnimator());
      recyclerView.setNestedScrollingEnabled(true);
    } else {
      newsAdapter.notifyDataSetChanged();
    }
  }

  public void detailBerita(View view) {
    Intent intent = new Intent(this, DetailBeritaActivity.class);
    startActivity(intent);
  }
}