package com.hernanbosqued.olx.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class ExtendedEntitiesModel {
    @SerializedName("media")
    public List<MediaEntityModel> media;
}
