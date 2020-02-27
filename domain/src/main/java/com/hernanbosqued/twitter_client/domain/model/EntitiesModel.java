package com.hernanbosqued.twitter_client.domain.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class EntitiesModel {
    @SerializedName("hashtags")
    public EntityModel[] hashtags;

    @SerializedName("urls")
    public EntityModel[] urls;

    @SerializedName("user_mentions")
    public EntityModel[] userMentions;

    @SuppressWarnings("unused")
    public static class EntityModel {
        @SerializedName("indices")
        public int[] indices;
    }
}
