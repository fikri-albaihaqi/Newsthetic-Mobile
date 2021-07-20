package com.example.newsthetic.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsthetic.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    Context context;
    ArrayList<NewsArticle> articles;

    public NewsAdapter(Context context, ArrayList<NewsArticle> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @NotNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_list, parent, false);

        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NewsAdapter.NewsViewHolder holder, int position) {
        holder.judul.setText(articles.get(position).getTitle().toString());
        holder.tanggal.setText(articles.get(position).getPublishedAt().toString());
        holder.publisher.setText(articles.get(position).getSource().toString());
        Picasso.get().load(articles.get(position).getUrlToImage())
                .resize(85, 65)
                .into(holder.gambar);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView judul, tanggal, publisher;
        ImageView gambar;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.judul);
            tanggal = itemView.findViewById(R.id.tanggal);
            publisher = itemView.findViewById(R.id.publisher);
            gambar = itemView.findViewById(R.id.gambar);
        }
    }
}
