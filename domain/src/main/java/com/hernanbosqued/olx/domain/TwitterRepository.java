package com.hernanbosqued.olx.domain;

import com.hernanbosqued.olx.domain.model.TwitterModel;

public interface TwitterRepository {
    void getTweets(final String query, final ModelCallbacks<TwitterModel> callback);
}
