package com.hernanbosqued.olx.domain;

public interface TwitterRepository {
    void getTweets(final String query, final ModelCallbacks<TwitterModel> callback);
}
