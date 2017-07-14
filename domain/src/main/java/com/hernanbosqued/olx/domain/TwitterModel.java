package com.hernanbosqued.olx.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TwitterModel {
    @SerializedName("statuses")
    public List<StatusModel> statuses;

    public static class StatusModel {
        @SerializedName("full_text")
        public String text;
        @SerializedName("user")
        public UserModel user;
        @SerializedName("entities")
        public EntitiesModel entities;

        @SerializedName("display_text_range")
        public int[] displayTextRange;

        public static class UserModel {
            @SerializedName("screen_name")
            public String screenName;
        }

        public static class EntitiesModel {
            @SerializedName("hashtags")
            public List<EntityModel> hashtags;

            @SerializedName("urls")
            public List<EntityModel> urls;

            @SerializedName("user_mentions")
            public List<EntityModel> userMentions;

            public static class EntityModel {
                @SerializedName("indices")
                public int[] indices;
            }
        }
    }
}

//"entities": {
//        "urls": [
//
//        ],
//        "hashtags": [
//        {
//        "text": "freebandnames",
//        "indices": [
//        20,
//        34
//        ]
//        }
//        ],
//        "user_mentions": [
//
//        ]
//        }