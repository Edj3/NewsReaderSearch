package com.mannmade.newsreadersearch;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * Created by Eg0 Jemima on 5/16/2016.
 */
public class JSONParser {
    public ArrayList<LinkedHashMap<String, String>> getJSONforString(String json){
        //One Array list to house all mappings of key value pairs
        ArrayList<LinkedHashMap<String, String>> ebayItemList = new ArrayList<>();

        try{
            //create JSON Object
            JSONArray readEbayArray = new JSONArray(json);
            //loop thru each item in jsonArray and store key value pairs in map for each object
            for(int i = 0; i < readEbayArray.length(); i++){
                JSONObject jsonItem = readEbayArray.getJSONObject(i);
                //Log.i("Listing Items", "Item " + i);
                Iterator<String> keys = jsonItem.keys();
                LinkedHashMap<String, String> itemMap = new LinkedHashMap<>();

                while(keys.hasNext()){
                    String key = keys.next();
                    String value = jsonItem.getString(key);
                    itemMap.put(key, value);
                }
                ebayItemList.add(itemMap);
            }
            //Log.i("Items in Array", "The count of the items in your array list is = " + ebayItemList.size());
        }catch(Exception e){
            e.printStackTrace();
        }
        return ebayItemList;
    }
}