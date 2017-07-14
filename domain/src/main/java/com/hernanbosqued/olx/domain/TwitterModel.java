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

        @SerializedName("extended_entities")
        public ExtendedEntitiesModel extendedEntities;

        @SerializedName("display_text_range")
        public int[] displayTextRange;

        public static class UserModel {
            @SerializedName("screen_name")
            public String screenName;

            @SerializedName("profile_image_url")
            public String profileImageUrl;
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

        public static class ExtendedEntitiesModel {

            @SerializedName("media")
            public List<MediaEntityModel> media;

            public static class MediaEntityModel {
                @SerializedName("media_url")
                public String mediaUrl;

                @SerializedName("type")
                public String type;
            }
        }
    }
}