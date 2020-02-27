package com.hernanbosqued.twitter_client;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

abstract class BasePresenter<M, V> {
    M model;
    private WeakReference<V> view;

    void setModel(@NonNull M model) {
        this.model = model;
        if (setupDone()) {
            updateView();
        }
    }

    private boolean setupDone() {
        return view != null && model != null;
    }

    void bindView(@NonNull V view) {
        this.view = new WeakReference<>(view);
    }

    V view() {
        if (view == null) {
            return null;
        } else {
            return view.get();
        }
    }

    void unbindView() {
        this.view = null;
    }

    protected abstract void updateView();
}
