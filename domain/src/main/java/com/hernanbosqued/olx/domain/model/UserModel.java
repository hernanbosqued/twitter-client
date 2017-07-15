package com.hernanbosqued.olx.domain.model;

import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("screen_name")
    public String screenName;

    @SerializedName("profile_image_url")
    public String profileImageUrl;
}
