package com.mannmade.newsreadersearch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;

/**
 * Created by Eg0 Jemima on 5/22/2016.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder>{
    private LruCache<String, Bitmap> imageCache;

    //constructor
    public ArticleAdapter(){  //initialize cache in constructor of adapter
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 10;  // using 1/10 of available mem
        if(imageCache == null){
            imageCache = new LruCache<String, Bitmap>(cacheSize){
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getByteCount() / 1024;
                }
            };
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView headlineView;
        public TextView dateView;
        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            headlineView = (TextView) v.findViewById(R.id.headline_list_title);
            dateView = (TextView) v.findViewById(R.id.headline_list_date);
            imageView = (ImageView) v.findViewById(R.id.headline_list_image);
        }
    }

    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.headline_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleAdapter.ViewHolder holder, int position) {
        final int index = holder.getAdapterPosition();
        holder.headlineView.setText(JSONParser.getInstance().jsonArrayList.get(position).getHeadline());
        holder.dateView.setText(JSONParser.getInstance().jsonArrayList.get(position).getDate());
        loadBitmap(position, holder);

        //open article
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ArticleActivity.class);
                intent.putExtra("index", index);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return JSONParser.getInstance().jsonArrayList.size();
    }

    public class ImageLoader extends AsyncTask<Object, Integer, Bitmap> {
        ImageView currentView = null;

        protected Bitmap doInBackground(Object... params) {
            currentView = (ImageView) params[0];
            final int position = (int) params[1];
            Bitmap headlineImage = null;
            try {
                URL imageURL = new URL("http://www.nytimes.com/" + JSONParser.getInstance().jsonArrayList.get(position).getImageUrl());
                headlineImage = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
            } catch (Exception e) {
                Log.e(e.getClass().getName(), e.getMessage());
            }
            //add bitmap to cache for next load
            addBitmapToCache(String.valueOf(position), headlineImage);
            //return downloaded image
            return headlineImage;

        }

        protected void onPostExecute(Bitmap result) {
            //use downloaded image for first load on UI thread
            if (result != null) {
                currentView.setImageBitmap(result);
            }
        }
    }

    //function to add to cache
    public void addBitmapToCache(String key, Bitmap bitmap){
        if(getBitmapFromCache(key) == null){
            imageCache.put(key, bitmap);
        }
    }

    //function to getBitmapFromCache
    public Bitmap getBitmapFromCache(String key){
        return imageCache.get(key);
    }

    public void loadBitmap(int id, ViewHolder vh){
        final String imageKey = String.valueOf(id);
        final Bitmap bitmap = getBitmapFromCache(imageKey);

        if(bitmap != null){
            vh.imageView.setImageBitmap(bitmap);
        }else{
            vh.imageView.setImageResource(R.drawable.ic_business);  //placeholder while image downloads
            new ImageLoader().execute(vh.imageView, id);
        }
    }
}
