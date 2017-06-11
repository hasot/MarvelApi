package com.example.root.marveltest.model;

/**
 * Created by root on 31.05.17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data {

    @SerializedName("offset")
    @Expose
    private String offset;
    @SerializedName("limit")
    @Expose
    private String limit;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("count")
    @Expose
    private String count;
    @SerializedName("results")
    @Expose
    private ArrayList<Result> results = null;

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public ArrayList<Result>  getResults() {
        return results;
    }

    public void setResults(ArrayList<Result>  results) {
        this.results = results;
    }

}





