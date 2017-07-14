package com.hernanbosqued.olx.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TwitterModel {

    @SerializedName("statuses")
    public List<StatusModel> statuses;

    public static class StatusModel {
        @SerializedName("text")
        public String text;
    }
}