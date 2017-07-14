package com.hernanbosqued.olx;

import android.content.Context;
import android.support.v4.app.Fragment;

abstract class BaseFragment<T> extends Fragment {
    protected T callbacks;

    protected abstract T getDummyCallbacks();

    @Override
    @SuppressWarnings("unchecked")
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callbacks = (T) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement fragment's callbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = getDummyCallbacks();
    }
}
