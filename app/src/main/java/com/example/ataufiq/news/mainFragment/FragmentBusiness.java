package com.example.ataufiq.news.mainFragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.ataufiq.news.MainContract;
import com.example.ataufiq.news.MainPresenter;
import com.example.ataufiq.news.R;
import com.example.ataufiq.news.Constants;
import com.example.ataufiq.news.adapter.NewsAdapter;
import com.example.ataufiq.news.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentBusiness extends Fragment implements MainContract.View{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<News> listNews;
    ProgressBar progressBar;
    MainPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_headlines, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        presenter= new MainPresenter(this);

        createRecylerView(view);
        setAdapter(view);

        presenter.getNews(Constants.ARTICLES_TOP_HEADLINES + "country=us"+"&category=business");

        return view;
    }

    private void createRecylerView(View view){
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    private void setAdapter(View view){
        listNews = new ArrayList<>();
        adapter = new NewsAdapter(view.getContext(), listNews);
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
}
