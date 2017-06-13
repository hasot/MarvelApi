package com.example.root.marveltest.api;


import com.example.root.marveltest.model.Example;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ApiService {

    /*
    Retrofit get annotation with our URL
    And our method that will return us the List of ContactList
    */
    @GET("v1/public/characters")
    Call<Example> getTasks(@QueryMap Map<String, String> options);


    @GET("v1/public/characters/{id}/comics ")
    Call<Example> getComics(@Path("id") String groupId, @QueryMap Map<String, String> options);


    @GET("v1/public/characters/{id}/events ")
    Call<Example> getEvents(@Path("id") String groupId, @QueryMap Map<String, String> options);


    @GET("v1/public/characters/{id}/series ")
    Call<Example> getSeries(@Path("id") String groupId, @QueryMap Map<String, String> options);


    @GET("v1/public/characters/{id}/stories ")
    Call<Example> getStories(@Path("id") String groupId, @QueryMap Map<String, String> options);

}