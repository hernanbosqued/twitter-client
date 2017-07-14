package com.hernanbosqued.olx;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<M, V> {
    protected M model;
    WeakReference<V> view;

    public void setModel(M model) {
        this.model = model;
        if (setupDone()) {
            updateView();
        }
    }

    protected boolean setupDone() {
        return view != null && model != null;
    }

    public void bindView(@NonNull V view) {
        this.view = new WeakReference<>(view);
    }

    protected V view() {
        if (view == null) {
            return null;
        } else {
            return view.get();
        }
    }

    public void unbindView() {
        this.view = null;
    }

    protected abstract void updateView();
}
