package com.example.root.marveltest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.root.marveltest.model.Result;

import java.util.List;

/**
 * Created by root on 13.06.17.
 */

public class StoriesAdapter extends ArrayAdapter<Result> {

   private List<Result>  resultList;

    public StoriesAdapter(Context context, List<Result> resultList) {
        super(context, android.R.layout.simple_list_item_1, resultList);
        this.resultList = resultList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Result result = resultList.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(android.R.layout.simple_list_item_1, null);
        }
        ((TextView) convertView.findViewById(android.R.id.text1))
                .setText(result.getTitle());

        return convertView;
    }
}
