package com.example.ataufiq.news.mainFragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.ataufiq.news.R;
import com.example.ataufiq.news.Constants;
import com.example.ataufiq.news.adapter.NewsAdapter;
import com.example.ataufiq.news.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentAnother extends Fragment {

    private RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    private ArrayList<News> listNews;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_another, container, false);

        createRecylerView(view);
        setAdapter(view);
        progressBar = view.findViewById(R.id.progressBar);
        getNews();

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

    public void getNews() {
        AndroidNetworking.get(Constants.ARTICLE_EVERYTHING + "q=Apple")
                .addHeaders("x-api-key", Constants.API_KEY)
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {

                    @Override
                    public void onResponse(JSONObject response) {

                        JSONArray articles_arry;
                        News listProduct;
                        try {
                            articles_arry = response.getJSONArray("articles");
                            for (int i = 0; i < articles_arry.length(); i++) {
                                JSONObject obj = articles_arry.getJSONObject(i);
                                listProduct = new News(obj.getString(Constants.KEY_ARTICLE_AUTOR),
                                        obj.getString(Constants.KEY_ARTICLE_TITLE),
                                        obj.getString(Constants.KEY_ARTICLE_DESCRIPTION),
                                        obj.getString(Constants.KEY_ARTICLE_URL),
                                        obj.getString(Constants.KEY_ARTICLE_URLTOIMAGE),
                                        obj.getString(Constants.KEY_ARTICLE_PUBLISHEDAT));
                                listNews.add(listProduct);

                            }
                            adapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}
