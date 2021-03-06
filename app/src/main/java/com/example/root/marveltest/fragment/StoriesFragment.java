package com.example.root.marveltest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.root.marveltest.R;
import com.example.root.marveltest.adapter.StoriesAdapter;
import com.example.root.marveltest.api.ApiService;
import com.example.root.marveltest.api.RetroClient;
import com.example.root.marveltest.model.Example;
import com.example.root.marveltest.model.Result;
import com.example.root.marveltest.utils.InternetConnection;
import com.example.root.marveltest.utils.PersonalMarvelApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 11.06.17.
 */

public class StoriesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private View parentView;
    private ArrayList<Result> charactersList;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private String id;


    private ListView listView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stories,
                container, false);

        listView = (ListView) view.findViewById(R.id.list_stories);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayoutStories);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        Bundle extras = getActivity().getIntent().getExtras();
        id = extras.getString("id");
        if (InternetConnection.checkConnection(getActivity().getApplicationContext())) {
            mSwipeRefreshLayout.post(new Runnable() {
                                         @Override
                                         public void run() {
                                             mSwipeRefreshLayout.setRefreshing(true);
                                             connectApi(id);
                                         }
                                     }
            );
        }
        return view;
    }


    @Override
    public void onRefresh() {
        connectApi(id);
    }


    private void connectApi(String id) {

        mSwipeRefreshLayout.setRefreshing(true);

        ApiService api = RetroClient.getApiService();
        Call<Example> callComics = api.getStories(id, PersonalMarvelApi.getKeyHash());

        callComics.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {


                if (response.isSuccessful()) {

                    charactersList = response.body().getData().getResults();
                    StoriesAdapter adapter = new StoriesAdapter(getActivity(), charactersList);
                    listView.setAdapter(adapter);
                    mSwipeRefreshLayout.setRefreshing(false);
                } else {
                    Snackbar.make(parentView, R.string.string_some_thing_wrong, Snackbar.LENGTH_LONG).show();
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}