package com.mannmade.newsreadersearch;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
            articleText.loadUrl(JSONParser.getInstance().jsonArrayList.get(index).articleUrl);
        }

        TextView articleHeader = (TextView) findViewById(R.id.article_header_text);
        articleHeader.setText(JSONParser.getInstance().jsonArrayList.get(index).headline);
        TextView articleBackButton = (TextView) findViewById(R.id.article_header_back_button);
        articleBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
