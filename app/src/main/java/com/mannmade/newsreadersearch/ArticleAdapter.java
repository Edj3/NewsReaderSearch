package com.mannmade.newsreadersearch;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Eg0 Jemima on 5/22/2016.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder>{

    //constructor
    public ArticleAdapter(){
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView headlineView;

        public ViewHolder(View v) {
            super(v);
            headlineView = (TextView) v.findViewById(R.id.headline_list_title);
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
        final ArticleAdapter.ViewHolder myHolder = holder;
        holder.headlineView.setText(JSONParser.getInstance().jsonArrayList.get(position).headline);

        //open article
        holder.headlineView.setOnClickListener(new View.OnClickListener() {
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
}
