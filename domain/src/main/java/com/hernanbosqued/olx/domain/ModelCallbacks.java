package com.hernanbosqued.olx.domain;

import com.hernanbosqued.olx.domain.model.ErrorModel;

public interface ModelCallbacks<T> {
    void onSuccess(T data);

    void onFail(ErrorModel errorModel);
}