package com.example.ataufiq.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    String title, description, url, image;
    TextView mTitleNews, mDescriptionNews;
    ImageView mImageNews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getDataFromAdapter();
        setData();
        readFullArticle(url);

    }

    private void getDataFromAdapter() {
        //find Intent from Main Activity
        Intent intent = getIntent();
        title = intent.getStringExtra(Constants.KEY_ARTICLE_TITLE);
        description = intent.getStringExtra(Constants.KEY_ARTICLE_DESCRIPTION);
        url = intent.getStringExtra(Constants.KEY_ARTICLE_URL);
        image = intent.getStringExtra(Constants.KEY_ARTICLE_URLTOIMAGE);

        mTitleNews = findViewById(R.id.tv_title);
        mDescriptionNews = findViewById(R.id.tv_deskripsi);
        mImageNews = findViewById(R.id.img_news);

    }

    private void setData() {
        //Set Title
        mTitleNews.setText(title);

        //Set Description
        if (!description.equals("null")) {
            mDescriptionNews.setText(description);
        } else {
            mDescriptionNews.setText("No Description");
        }

        //Set Image
        if (!image.equals("null")) {
            Glide.with(this)
                    .load(image)
                    .into(mImageNews);
        } else {
            mImageNews.setImageResource(R.drawable.no_image);
        }
    }

    private void readFullArticle(final String url) {

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
