package com.hernanbosqued.olx.domain.model;

import com.google.gson.annotations.SerializedName;

public class EntitiesModel {
    @SerializedName("hashtags")
    public EntityModel[] hashtags;

    @SerializedName("urls")
    public EntityModel[] urls;

    @SerializedName("user_mentions")
    public EntityModel[] userMentions;

    public static class EntityModel {
        @SerializedName("indices")
        public int[] indices;
    }
}
