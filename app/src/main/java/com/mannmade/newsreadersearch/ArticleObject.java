package com.mannmade.newsreadersearch;

/**
 * Created by Eg0 Jemima on 5/22/2016.
 */
public class ArticleObject {
    public String headline;
    public String articleUrl;
    public String imageUrl;
    public String author;
    public String date;

    public ArticleObject(String headline, String articleUrl, String imageUrl, String author, String date){
        this.headline = headline;
        this.articleUrl = articleUrl;
        this.imageUrl = imageUrl;
        this.author = author;
        this.date = date;
    }
}
