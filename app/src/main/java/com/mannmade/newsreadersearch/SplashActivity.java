package com.mannmade.newsreadersearch;

import android.content.Intent;
import android.os.AsyncTask;
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

        new DownloadTask().execute();
    }

    public class DownloadTask extends AsyncTask<Void, Integer, Integer> {
        protected Integer doInBackground(Void... voids) {
            try{
                String pattern = "yyyyMMdd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
                String today = simpleDateFormat.format(new Date());
                Log.v("Todays Date", today);
                final String nytURL = "https://api.nytimes.com/svc/search/v2/articlesearch.json?format=json&begin_date=" + today + "&api-key=a8457610b68381085a3fff38d6a36337:6:74255139";
                ConnectionManager urlConnect = ConnectionManager.getInstance();
                String json = urlConnect.getConnectionToURL(nytURL);
                Log.i("NYT URL", nytURL);
                Log.v("connection result", json);
                JSONParser.getInstance().getJSONforString(json);
                return 1;
            }catch(Exception e){
                Log.e(e.getClass().getName(), e.getMessage());
                return 0;
            }
        }

        protected void onPostExecute(Integer result) {
            if(result == 1){
                Intent intent = new Intent(getApplicationContext(), HeadlinesActivity.class);
                startActivity(intent);
            }
        }
    }
}
