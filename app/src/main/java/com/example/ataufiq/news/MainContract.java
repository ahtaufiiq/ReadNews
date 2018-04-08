package com.example.ataufiq.news;

import com.example.ataufiq.news.model.News;

import java.util.List;

public interface MainContract {

    interface View{
        void addData(List<News> news);
        void showProgress();
        void dismissProgress();
    }

    interface Presenter{
        void getNews(String url);
    }
}
