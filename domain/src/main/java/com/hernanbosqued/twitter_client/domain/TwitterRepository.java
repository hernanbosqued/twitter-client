package com.hernanbosqued.twitter_client.domain;

import com.hernanbosqued.twitter_client.domain.model.TwitterModel;

public interface TwitterRepository {
    void getTweets(final String query, final ModelCallbacks<TwitterModel> callback);
}
