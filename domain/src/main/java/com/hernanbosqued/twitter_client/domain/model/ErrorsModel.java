package com.hernanbosqued.twitter_client.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class ErrorsModel {
    @SerializedName("errors")
    public List<ErrorModel> errors;
}
