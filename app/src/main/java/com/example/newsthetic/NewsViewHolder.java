package com.example.newsthetic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class NewsViewHolder extends RecyclerView.ViewHolder {
    TextView judul, tanggal, publisher;
    ImageView gambar;
    ConstraintLayout newsList;

    public NewsViewHolder(View itemView) {
        super(itemView);

        judul = itemView.findViewById(R.id.judul);
        tanggal = itemView.findViewById(R.id.tanggal);
        publisher = itemView.findViewById(R.id.publisher);
        gambar = itemView.findViewById(R.id.gambar);
        newsList = itemView.findViewById(R.id.list_layout);
    }

    public void bind(String judul, String tanggal, String publisher, String gambar, Context context) {
        this.judul.setText(judul);
        this.tanggal.setText(tanggal);
        this.publisher.setText(publisher);
        Picasso.with(context).load(gambar)
                .resize(120, 90)
                .centerCrop()
                .into(this.gambar);
    }

    static NewsViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list, parent, false);

        return new NewsViewHolder(view);
    }
}
