package com.example.root.marveltest.model;

import com.google.gson.Gson;

/**
 * Created by root on 07.06.17.
 */

public class JsonSerializer {

    public static String toJson(Object o) {
        return new Gson().toJson(o);
    }

    public static Object fromJson(String json, Class aClass) {
        return new Gson().fromJson(json, aClass);
    }
}