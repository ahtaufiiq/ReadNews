package com.example.ataufiq.news;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.ataufiq.news.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;

    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getNews(String url) {
        AndroidNetworking.get(url)
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
                                mView.addData(Collections.singletonList(listProduct));

                            }
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
