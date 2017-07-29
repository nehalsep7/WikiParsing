package com.example.nehal.wikiparsing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nehal.wikiparsing.Model.WikiPage;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nehal on 28/7/17.
 */

public class WikiAdapter extends RecyclerView.Adapter<WikiAdapter.MyViewHolder> {
    private List<WikiPage> pagesList = new ArrayList<>();
    private Context context;
    @Override
    public WikiAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wiki_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WikiAdapter.MyViewHolder holder, int position) {
        WikiPage page = pagesList.get(position);
        if(!TextUtils.isEmpty(page.getTitle()))
             holder.headingText.setText(page.getTitle());
        if(page.getTerms() != null && page.getTerms().getDescription().size() > 0 &&! TextUtils.isEmpty(page.getTerms().getDescription().get(0)))
            holder.descText.setText(page.getTerms().getDescription().get(0));
        if(page.getThumbnail() != null && !TextUtils.isEmpty(page.getThumbnail().getSource()))
            Picasso.with(context).load(page.getThumbnail().getSource()).resize(300, 400).placeholder(R.mipmap.ic_launcher).into(holder.image);


    }

    public WikiAdapter(Context context, List<WikiPage> pages){
        this.pagesList = pages;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return pagesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView headingText,descText;
        public ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            headingText = (TextView)itemView.findViewById(R.id.headingText);
            descText = (TextView)itemView.findViewById(R.id.descText);
            image = (ImageView)itemView.findViewById(R.id.imageView);
        }
    }
}
