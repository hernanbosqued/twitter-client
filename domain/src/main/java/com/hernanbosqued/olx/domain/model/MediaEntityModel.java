package com.hernanbosqued.olx.domain.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class MediaEntityModel {
    @SerializedName("media_url")
    public String mediaUrl;

    @SerializedName("type")
    public String type;
}
