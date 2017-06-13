package com.example.root.marveltest.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.marveltest.R;

import com.example.root.marveltest.adapter.ComicsAdapter;
import com.example.root.marveltest.api.ApiService;
import com.example.root.marveltest.api.RetroClient;
import com.example.root.marveltest.model.Example;
import com.example.root.marveltest.model.Result;
import com.example.root.marveltest.utils.InternetConnection;
import com.example.root.marveltest.utils.PersonalMarvelApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 09.06.17.
 */

public class ComicsFragment extends Fragment implements  SwipeRefreshLayout.OnRefreshListener {


    private RecyclerView recyclerView;
    private ComicsAdapter adapter;
    private View parentView;
    private ArrayList<Result> charactersList;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private String id;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_comics,
                container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        Bundle extras = getActivity().getIntent().getExtras();
         id = extras.getString("id");
        Log.d("dddd", id);
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
        Call<Example> callComics = api.getComics(id, PersonalMarvelApi.getKeyHash());
        callComics.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                if (response.isSuccessful()) {

                    charactersList = response.body().getData().getResults();

                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
                    adapter = new ComicsAdapter(getActivity(), charactersList);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
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

