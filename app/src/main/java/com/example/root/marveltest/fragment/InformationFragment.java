package com.example.root.marveltest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.marveltest.R;

/**
 * Created by root on 07.06.17.
 */

public class InformationFragment extends Fragment {

    private TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information,
                container, false);

        textView = (TextView) view.findViewById(R.id.text_information);

        return view;
    }
}
