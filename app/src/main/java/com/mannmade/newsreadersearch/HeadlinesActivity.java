package com.mannmade.newsreadersearch;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

public class HeadlinesActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    boolean searchDisplayed = false;
    SearchView articleSearchView;
    ListView articleSearchListView;
    Filter listFilter;

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
        articleSearchView = (SearchView)findViewById(R.id.search_view);
        articleSearchListView = (ListView) findViewById(R.id.search_list_view);
        ArrayAdapter articleListAdapter = new ArrayAdapter<>(this, R.layout.search_list_item, JSONParser.getInstance().getJsonArrayList());
        listFilter = articleListAdapter.getFilter();
        articleSearchListView.setAdapter(articleListAdapter);
        articleSearchListView.setTextFilterEnabled(true);
        setupSearchView();

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
                        headlineTabs.setVisibility(View.GONE);
                }else{
                    searchButton.setImageResource(R.drawable.ic_search);
                    if(searchLayout != null)
                        searchLayout.setVisibility(View.GONE);
                    if(headlineTabs != null)
                        headlineTabs.setVisibility(View.VISIBLE);
                }
                searchButton.bringToFront();
            }
        });
    }

    private void setupSearchView() {
        articleSearchView.setOnQueryTextListener(this);
        articleSearchListView.setTextFilterEnabled(true);
        articleSearchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ArticleActivity.class);
                intent.putExtra("index", JSONParser.getInstance().getJsonArrayList().indexOf(parent.getAdapter().getItem(position)));
                startActivity(intent);
            }
        });
    }

    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            articleSearchListView.clearTextFilter();
            listFilter.filter(null);
        } else {
            listFilter.filter(newText);
        }
        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        return false;
    }
}
