package com.mannmade.newsreadersearch;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by Eg0 Jemima on 5/16/2016.
 */
public class JSONParser {//Singleton Class to pass JSON String provided
    //member variables and constructor need to be private for singletons! Only allow others to access needed getters
    private static JSONParser mInstance = null;
    public ArrayList<ArticleObject> jsonArrayList;

    //private constructor
    private JSONParser(){}

    public static JSONParser getInstance(){
        if(mInstance == null){
            mInstance = new JSONParser();
        }
        return mInstance;
    }

    public void getJSONforString(String json){
        //One Array list to house all mappings of key value pairs
        jsonArrayList = new ArrayList<>();

        try{
            //create JSON Object
            JSONObject readObject = new JSONObject(json);
            JSONObject responseObject = readObject.getJSONObject("response");
            JSONArray docArray = responseObject.getJSONArray("docs");
            //loop thru each item in jsonArray and store key value pairs in map for each object
            for(int i = 0; i < docArray.length(); i++){
                JSONObject jsonItem = docArray.getJSONObject(i);

                //webURL for entire article
                String webUrl = jsonItem.getString("web_url");

                //date for article
                String pubDate = jsonItem.getString("pub_date");

                //headline object
                JSONObject headlineItem = jsonItem.getJSONObject("headline");
                Log.i("Listing Items", "Item " + i);
                //Iterator<String> keys = jsonItem.keys();
                ArticleObject article;
                String headline = headlineItem.getString("main");

                //multimedia array
                JSONArray multimediaItem = jsonItem.getJSONArray("multimedia");
                String imageUrl = multimediaItem.getJSONObject(0).getString("url"); //get first image to use as image of list item

                //author
                String author = "";
                Object jsonOb = jsonItem.get("byline");
                if(jsonOb instanceof JSONArray){
                    author = "";
                }else{
                    author = jsonItem.getJSONObject("byline").getString("original");
                }

                article = new ArticleObject(headline, webUrl, imageUrl, author, pubDate);
                String currentItemString = article.headline + ", " + article.articleUrl + ", " + article.imageUrl + ", " + article.author + ", " + article.date;
                Log.i("Json Item", currentItemString);
                jsonArrayList.add(article);
            }
        }catch(Exception e){
            Log.e("NewsReaderSearch", "JsonParsingError", e);
        }
    }
}