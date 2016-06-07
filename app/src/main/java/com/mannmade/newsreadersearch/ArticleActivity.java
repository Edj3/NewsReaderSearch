package com.mannmade.newsreadersearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        int index = -1;
        if (getIntent() != null){
            index = getIntent().getIntExtra("index", -1);
        }

        if(index >= 0){
            WebView articleText = (WebView) findViewById(R.id.article_content);
            articleText.setWebViewClient(new WebViewClient());
            articleText.getSettings().setJavaScriptEnabled(true);
            articleText.loadUrl(JSONParser.getInstance().getJsonArrayList().get(index).getArticleUrl());
        }

        TextView articleHeader = (TextView) findViewById(R.id.article_header_text);
        articleHeader.setText(JSONParser.getInstance().getJsonArrayList().get(index).getHeadline());
    }
}
