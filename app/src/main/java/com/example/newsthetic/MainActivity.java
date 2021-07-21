package com.example.newsthetic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsthetic.model.NewsArticle;
import com.squareup.picasso.Picasso;

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
  List<NewsArticle> newsArticles;
  TextView judul_berita, tanggal_berita, publisher_berita;
  ImageView gambar_berita;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    recyclerView = findViewById(R.id.list_berita);
    search = findViewById(R.id.search);
//    cari = findViewById(R.id.cari);
    country = "id";
    newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
    judul_berita = findViewById(R.id.judul_berita);
    tanggal_berita = findViewById(R.id.tanggal_berita);
    publisher_berita = findViewById(R.id.publisher_berita);
    gambar_berita = findViewById(R.id.gambar_berita);

    if (country == "id") {
      loadNews(country);
    }


    search.setOnEditorActionListener(new TextView.OnEditorActionListener(){

      @Override
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEND) {
          loadSearchResult(search.getText().toString());
          search.setText("");
          return true;
        }else{
          return false;
        }
      }
    });

//    cari.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        newsViewModel.init();
//        loadSearchResult(search.getText().toString());
//        search.setText("");
//      }
//    });
  }

  public void loadNews(String country) {
    newsViewModel.init();
    newsViewModel.getNews(country).observe(this, newsResponse -> {
      List<NewsArticle> newsArticles = newsResponse.getArticles();
      articleArrayList.clear();
      Log.e("Isinya1", articleArrayList.toString());
      articleArrayList.addAll(newsArticles);
      Log.e("Isinya", articleArrayList.toString());
      newsAdapter.notifyDataSetChanged();
      Log.e("Panjang", String.valueOf(newsAdapter.getItemCount()));
      loadHeadlines(articleArrayList);
    });
    setupRecyclerView();
  }

  public void loadSearchResult(String search) {
//    newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
    newsViewModel.getNewsBySearch(search).observe(this, newsResponse -> {
      newsArticles = newsResponse.getArticles();
      articleArrayList.clear();
      Log.e("Isinya1", articleArrayList.toString());
      articleArrayList.addAll(newsArticles);
      newsAdapter.notifyDataSetChanged();
      Log.e("Isinya", articleArrayList.toString());
      Log.e("Panjang", String.valueOf(newsAdapter.getItemCount()));
    });
    setupRecyclerView();
  }

  public void loadHeadlines(ArrayList<NewsArticle> headline) {
    Log.e("Headline", headline.get(0).toString());
    judul_berita.setText(articleArrayList.get(0).getTitle());
    tanggal_berita.setText(articleArrayList.get(0).getPublishedAt());
    publisher_berita.setText(articleArrayList.get(0).getSource().getName());
    Picasso.with(this).load(articleArrayList.get(0).getUrlToImage()).fit().into(gambar_berita);
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
}