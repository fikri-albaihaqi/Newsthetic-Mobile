package com.example.newsthetic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailBeritaActivity extends AppCompatActivity {
  String titleNews, authorNews, tanggalNews, publisherNews, imageNews, descriptionNews, urlNews;
  String formatedURL = "sumber: ";
  private TextView tvTitle;
  private TextView tvAuthor;
  private TextView tvPublisher;
  private TextView tvContent;
  private TextView tvTanggal;
  private TextView tvUrl;
  private ImageView tvImage;
  private Button webSelanjutnya;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail_berita);

    tvTitle = findViewById(R.id.judul_detail_berita);
    tvTanggal = findViewById(R.id.tanggal_detail_berita);
    tvAuthor = findViewById(R.id.author_detail_berita);
    tvPublisher = findViewById(R.id.publisher_detail_berita);
    tvContent = findViewById(R.id.content_berita);
    tvUrl = findViewById(R.id.urlDetail);
    tvImage = findViewById(R.id.image_detail_berita);
    webSelanjutnya = findViewById(R.id.button_selanjutnya);

    getData();
    setData();

      webSelanjutnya.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(DetailBeritaActivity.this, NewsWebView.class).putExtra("url", urlNews));
      }
    });
  }
  public void getData(){

    Bundle bundle = getIntent().getExtras();
    if(getIntent().hasExtra("title")
            && getIntent().hasExtra("time")
            && getIntent().hasExtra("image")
            && getIntent().hasExtra("publisher")
            && getIntent().hasExtra("author")
            && getIntent().hasExtra("description")
            && getIntent().hasExtra("url"))
    {
      titleNews = bundle.getString("title");
      tanggalNews = bundle.getString("time");
      imageNews = bundle.getString("image");
      publisherNews = bundle.getString("publisher");
      authorNews = bundle.getString("author");
      descriptionNews = bundle.getString("description");
      urlNews = bundle.getString("url");

    }
  }

  public void setData(){
    tvTitle.setText(titleNews);
    tvTanggal.setText(tanggalNews);
    tvAuthor.setText(authorNews);
    tvPublisher.setText(publisherNews);
    tvContent.setText(descriptionNews);
    tvUrl.setText(formatedURL+urlNews);
    Picasso.get().load(imageNews).fit().centerCrop().into(tvImage);
  }
}