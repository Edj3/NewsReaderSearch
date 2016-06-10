package com.mannmade.newsreadersearch;

/**
 * Created by Eg0 Jemima on 6/10/2016.
 */
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

public class NewsReaderUnitTest {
    @Test
    @Before
    public void isConnectionManagerNotNull(){
        assertNotNull(ConnectionManager.getInstance());
    }

    @Test
    @Before
    public void doesConnectionManagerReturnJSONString(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String pattern = "yyyyMMdd";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
                    String today = simpleDateFormat.format(new Date());
                    final String nytURL = "https://api.nytimes.com/svc/search/v2/articlesearch.json?format=json&begin_date=" + today + "&api-key=a8457610b68381085a3fff38d6a36337:6:74255139";
                    assertNotNull(ConnectionManager.getInstance().getConnectionToURL(nytURL));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }
    @Test
    public void isJSONParserNotNull(){
        assertNotNull(JSONParser.getInstance());
    }

    @Test
    public void isJSONArrayListPopulated(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String pattern = "yyyyMMdd";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
                    String today = simpleDateFormat.format(new Date());
                    final String nytURL = "https://api.nytimes.com/svc/search/v2/articlesearch.json?format=json&begin_date=" + today + "&api-key=a8457610b68381085a3fff38d6a36337:6:74255139";
                    JSONParser.getInstance().getJSONforString(ConnectionManager.getInstance().getConnectionToURL(nytURL));
                    assertNotNull(JSONParser.getInstance().getJsonArrayList());
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Test
    public void checkJsonArrayContents(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String pattern = "yyyyMMdd";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
                    String today = simpleDateFormat.format(new Date());
                    final String nytURL = "https://api.nytimes.com/svc/search/v2/articlesearch.json?format=json&begin_date=" + today + "&api-key=a8457610b68381085a3fff38d6a36337:6:74255139";
                    JSONParser.getInstance().getJSONforString(ConnectionManager.getInstance().getConnectionToURL(nytURL));
                    assertTrue(JSONParser.getInstance().getJsonArrayList().size() > 0);
                    if(JSONParser.getInstance().getJsonArrayList().size() > 0){
                        for (ArticleObject a: JSONParser.getInstance().getJsonArrayList()) {
                            assertNotNull(a.getArticleUrl());
                            assertNotNull(a.getAuthor());
                            assertNotNull(a.getDate());
                            assertNotNull(a.getHeadline());
                            assertNotNull(a.getImageUrl());
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
