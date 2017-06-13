package com.example.root.marveltest.adapter;

/**
 * Created by root on 04.06.17.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.marveltest.R;
import com.example.root.marveltest.activity.CharacterActivity;
import com.example.root.marveltest.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class ComicsAdapter extends RecyclerView.Adapter<ComicsAdapter.MyViewHolder> {


    private Context mContext;
    private List<Result> resultList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;
        private CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);

            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }


    public ComicsAdapter(Context mContext, List<Result> characterList) {
        this.mContext = mContext;
        this.resultList = characterList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.characters_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Result result = resultList.get(position);

        holder.title.setText(result.getTitle());
        // loading album cover using Glide library
        Picasso.with(mContext)
                .load(result.getThumbnail().getPath() + "." + result.getThumbnail().getExtension())
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }
}