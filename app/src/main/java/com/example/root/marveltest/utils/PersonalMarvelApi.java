package com.example.root.marveltest.utils;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by root on 13.06.17.
 */

public class PersonalMarvelApi {

    private static final String API_TS = "20";
    private static final String API_KEY = "7fa7c57fbd7cd942ec4123f516d7690f";
    private static final String API_HASH = "74e9c8761fda39738459c9f7622e98b6";

    private static HashMap<String, String> hashKey;

    public static HashMap getKeyHash(){

        hashKey = new HashMap<>();

        hashKey.put("ts", API_TS);
        hashKey.put("apikey", API_KEY);
        hashKey.put("hash", API_HASH);

        return hashKey;
    }

    public static HashMap getKeyHashMain(){

        hashKey = new HashMap<>();
        hashKey.put("limit", "" + 100);
        hashKey.put("offset", "" + 500);
        hashKey.put("ts", API_TS);
        hashKey.put("apikey", API_KEY);
        hashKey.put("hash", API_HASH);

        return hashKey;
    }
}
