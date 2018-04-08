package com.example.ataufiq.news;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getDataFromAdapter();
        readFullArticle(url);

    }

    private void getDataFromAdapter(){
        //find Intent from Main Activity
        Intent intent = getIntent();
        String title = intent.getStringExtra(Constants.KEY_ARTICLE_TITLE);
        String description = intent.getStringExtra(Constants.KEY_ARTICLE_DESCRIPTION);
        url = intent.getStringExtra(Constants.KEY_ARTICLE_URL);
        String image = intent.getStringExtra(Constants.KEY_ARTICLE_URLTOIMAGE);

        TextView mTitleNews =  findViewById(R.id.tv_title);
        TextView mDescriptionNews =  findViewById(R.id.tv_deskripsi);
        ImageView mImageNews =  findViewById(R.id.img_news);

        mTitleNews.setText(title);
        mDescriptionNews.setText(description);
        Glide.with(this)
                .load(image)
                .into(mImageNews);
    }

    private void readFullArticle(final String url){

        final Button button = findViewById(R.id.btn_read_all);
        final WebView webView = findViewById(R.id.webView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);

            }
        });
    }
}
