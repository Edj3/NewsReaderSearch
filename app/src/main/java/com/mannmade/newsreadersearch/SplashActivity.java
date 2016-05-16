package com.mannmade.newsreadersearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.RunnableFuture;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final ConnectionManager myConnect = ConnectionManager.getInstance();

        String pattern = "yyyyMMdd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
        String today = simpleDateFormat.format(new Date());
        Log.v("Todays Date", today);

        final String nytURL = "https://api.nytimes.com/svc/search/v2/articlesearch.json?format=json&query=smoking&offset=1&api-key=a8457610b68381085a3fff38d6a36337:6:74255139";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String json = myConnect.getConnectionToURL(nytURL);
                    Log.v("connection result", json);
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }).start();

        Log.e("JSON", myConnect.json);
    }
}
