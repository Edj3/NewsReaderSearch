package com.mannmade.newsreadersearch;

/**
 * Created by Eg0 Jemima on 5/22/2016.
 */
public class ArticleObject {
    public String headline;
    public String content;
    public String author;
    public int likes;
    public int comments;
    public int category;

    public ArticleObject(String headline){
        this.headline = headline;
    }
}
