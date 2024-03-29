package com.hernanbosqued.twitter_client.domain.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class StatusModel {

    @SerializedName("full_text")
    public String text;

    @SerializedName("user")
    public UserModel user;

    @SerializedName("entities")
    public EntitiesModel entities;

    @SerializedName("extended_entities")
    public ExtendedEntitiesModel extendedEntities;

    @SerializedName("display_text_range")
    public int[] displayTextRange;

    @SerializedName("retweeted_status")
    public StatusModel retweetedStatus;

}
