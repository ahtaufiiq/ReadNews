package com.example.ataufiq.news.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ataufiq.news.DetailActivity;
import com.example.ataufiq.news.R;
import com.example.ataufiq.news.Constants;
import com.example.ataufiq.news.model.News;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<News> listNews;

    public NewsAdapter(Context context, ArrayList<News> listNews) {
        this.context = context;
        this.listNews = listNews;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_news, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitleNews;
        private ImageView mImageNews;
        private CardView cardView;

        private ViewHolder(View itemView) {
            super(itemView);

            mTitleNews = itemView.findViewById(R.id.tv_title);
            mImageNews = itemView.findViewById(R.id.img_news);
            cardView = itemView.findViewById(R.id.card_product);
        }
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
        final News news = listNews.get(position);

        holder.mTitleNews.setText(news.getTitle());

        if (!news.getUrlToImage().equals("null")){
        Glide.with(context)
                .load(news.getUrlToImage())
                .into(holder.mImageNews);
        }else {
            holder.mImageNews.setImageResource(R.drawable.no_image);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(Constants.KEY_ARTICLE_TITLE, news.getTitle());
                intent.putExtra(Constants.KEY_ARTICLE_DESCRIPTION, news.getDescription());
                intent.putExtra(Constants.KEY_ARTICLE_URL, news.getUrl());
                intent.putExtra(Constants.KEY_ARTICLE_URLTOIMAGE, news.getUrlToImage());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }
}
