package com.hernanbosqued.twitter_client.domain;

import com.hernanbosqued.twitter_client.domain.model.ErrorModel;

public interface ModelCallbacks<T> {
    void onSuccess(T data);

    void onFail(ErrorModel errorModel);
}