package com.example.root.marveltest.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.marveltest.R;
import com.example.root.marveltest.adapter.CharactersAdapter;
import com.example.root.marveltest.api.ApiService;
import com.example.root.marveltest.api.RetroClient;
import com.example.root.marveltest.model.Example;
import com.example.root.marveltest.model.Result;
import com.example.root.marveltest.utils.InternetConnection;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 07.06.17.
 */

public class ListCharactersFragment extends Fragment {


    private static final String API_TS = "20";
    private static final String API_KEY = "7fa7c57fbd7cd942ec4123f516d7690f";
    private static final String API_HASH = "74e9c8761fda39738459c9f7622e98b6";

    private RecyclerView recyclerView;
    private CharactersAdapter adapter;
    private View parentView;
    private ArrayList<Result> charactersList;
    private HashMap<String, String> connect;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main,
                container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        if (InternetConnection.checkConnection(getActivity().getApplicationContext())) {
            connectApi();
        }
        return view;
    }

    void connectApi() {

        connect = new HashMap<>();
        connect.put("limit", "" + 100);
        connect.put("offset", "" + 500);
        connect.put("ts", API_TS);
        connect.put("apikey", API_KEY);
        connect.put("hash", API_HASH);
        final ProgressDialog dialog;

        dialog = new ProgressDialog(getActivity());
        dialog.setTitle(getString(R.string.string_getting_json_title));
        dialog.setMessage(getString(R.string.string_getting_json_message));
        dialog.show();

        ApiService api = RetroClient.getApiService();
        Call<Example> callComics = api.getTasks(connect);

        callComics.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                dialog.dismiss();

                if (response.isSuccessful()) {

                    charactersList = response.body().getData().getResults();

                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
                    adapter = new CharactersAdapter(getActivity(), charactersList);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                } else {
                    Snackbar.make(parentView, R.string.string_some_thing_wrong, Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                dialog.dismiss();
            }
        });

    }
}
