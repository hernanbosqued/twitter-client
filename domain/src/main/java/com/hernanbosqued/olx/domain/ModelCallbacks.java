package com.hernanbosqued.olx.domain;

public interface ModelCallbacks<T> {
    void onSuccess(T data);

    void onFail(ErrorModel.Error error);
}