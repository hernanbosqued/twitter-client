package com.hernanbosqued.olx.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TwitterModel {
    @SerializedName("statuses")
    public List<StatusModel> statuses;

}