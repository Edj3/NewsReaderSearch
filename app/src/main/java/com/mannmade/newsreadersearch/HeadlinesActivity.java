package com.mannmade.newsreadersearch;

import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class HeadlinesActivity extends AppCompatActivity {
    boolean searchDisplayed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headlines);

        RecyclerView articleList = (RecyclerView) findViewById(R.id.headline_articles_list);
        if(articleList != null){
            articleList.setLayoutManager(new LinearLayoutManager(this));
            articleList.setAdapter(new ArticleAdapter());
        }

        final TabLayout headlineTabs = (TabLayout) findViewById(R.id.headline_tabs);
        if(headlineTabs != null){
            headlineTabs.addTab(headlineTabs.newTab().setText(getResources().getString(R.string.articles)));
            headlineTabs.addTab(headlineTabs.newTab().setText(getResources().getString(R.string.categories)));
            headlineTabs.addTab(headlineTabs.newTab().setText(getResources().getString(R.string.settings)));
        }

        final ImageButton searchButton = (ImageButton) findViewById(R.id.search_button);
        final LinearLayout searchLayout = (LinearLayout) findViewById(R.id.search_layout);

        if(searchButton != null)
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchDisplayed = !searchDisplayed; //toggle boolean
                if(searchDisplayed){
                    searchButton.setImageResource(R.drawable.ic_close_search);
                    if(searchLayout != null)
                        searchLayout.setVisibility(View.VISIBLE);
                    if(headlineTabs != null)
                        headlineTabs.setVisibility(View.INVISIBLE);
                }else{
                    searchButton.setImageResource(R.drawable.ic_search);
                    if(searchLayout != null)
                        searchLayout.setVisibility(View.INVISIBLE);
                    if(headlineTabs != null)
                        headlineTabs.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
