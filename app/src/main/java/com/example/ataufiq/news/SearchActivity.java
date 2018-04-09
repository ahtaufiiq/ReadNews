package com.example.ataufiq.news;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ataufiq.news.adapter.NewsAdapter;
import com.example.ataufiq.news.model.News;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements MainContract.View{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<News> listNews;
    ActionBar actionBar;
    ProgressBar progressBar;
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        presenter= new MainPresenter(this);
        progressBar=findViewById(R.id.progressBar);
        createRecylerView();
        setAdapter();

        handleIntent(getIntent());
    }


    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            progressBar.setVisibility(View.VISIBLE);
            String query = intent.getStringExtra(SearchManager.QUERY);
            actionBar.setTitle(query);
            presenter.getNews(Constants.ARTICLE_EVERYTHING+"q="+query);
        }
    }

    private void createRecylerView(){
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setAdapter(){
        listNews = new ArrayList<>();
        adapter = new NewsAdapter(this, listNews);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void addData(List<News> newsList) {
        for (News news:newsList){
            listNews.add(news);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
