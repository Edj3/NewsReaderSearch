package com.mannmade.newsreadersearch;

import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HeadlinesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headlines);

        TabLayout headlineTabs = (TabLayout) findViewById(R.id.headline_tabs);
        if(headlineTabs != null){
            headlineTabs.addTab(headlineTabs.newTab().setText(getResources().getString(R.string.articles)));
            headlineTabs.addTab(headlineTabs.newTab().setText(getResources().getString(R.string.categories)));
            headlineTabs.addTab(headlineTabs.newTab().setText(getResources().getString(R.string.settings)));
        }

    }
}
