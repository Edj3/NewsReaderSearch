package com.mannmade.newsreadersearch;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Eg0 Jemima on 5/16/2016.
 */
public class ConnectionManager {
    private static ConnectionManager mInstance = null;
    public String json = "";

    private ConnectionManager(){

    }

    public static ConnectionManager getInstance(){
        if(mInstance == null)
        {
            mInstance = new ConnectionManager();
        }
        return mInstance;
    }

    public String getConnectionToURL(String givenLink){
        //use string builder because every normal string concatenation creates a new string object. StringBuilder manages it all into one and is more efficient
        StringBuilder jsonBuilder = new StringBuilder();
        try{
            //Connect to given URL
            URL givenURL = new URL(givenLink);
            HttpURLConnection connection = (HttpURLConnection) givenURL.openConnection();
            InputStream iStream = connection.getInputStream();

            //Read in page
            BufferedReader reader = new BufferedReader(new InputStreamReader(iStream, "UTF-8"));
            String lineFromFile;

            //loop through and create string by concatenating all lines from page
            while ((lineFromFile = reader.readLine()) != null){
                jsonBuilder.append(lineFromFile);
                jsonBuilder.append(("\n"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        json = jsonBuilder.toString();
        Log.v("New JSON", json);

        return json;
    }
}