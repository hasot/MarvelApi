package com.example.root.marveltest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.root.marveltest.R;

/**
 * Created by root on 09.06.17.
 */

public class ComicsFragment extends Fragment {


    private ListView listView;
    private TextView textView;
    Adapter adapter;

    public ComicsFragment() {
        // Required empty public constructor
    }


    public static ComicsFragment newInstance(String url){
        ComicsFragment fragment = new ComicsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("URL", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tabs,
                container, false);

        listView = (ListView) view.findViewById(R.id.list_events);
        textView = (TextView) view.findViewById(R.id.tab);


        return view;
    }
}
