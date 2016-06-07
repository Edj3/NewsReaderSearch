package com.mannmade.newsreadersearch;

/**
 * Created by Eg0 Jemima on 5/22/2016.
 */
public class ArticleObject {
    private String headline;
    private String articleUrl;
    private String imageUrl;
    private String author;
    private String date;

    public ArticleObject(String headline, String articleUrl, String imageUrl, String author, String date){
        this.headline = headline;
        this.articleUrl = articleUrl;
        this.imageUrl = imageUrl;
        this.author = author;
        this.date = date;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return this.headline;
    }
}
