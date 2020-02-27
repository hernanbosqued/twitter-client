package com.hernanbosqued.twitter_client.domain.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class UserModel {
    @SerializedName("screen_name")
    public String screenName;

    @SerializedName("profile_image_url")
    public String profileImageUrl;
}
