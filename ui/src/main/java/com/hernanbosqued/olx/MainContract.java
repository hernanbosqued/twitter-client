package com.hernanbosqued.olx;

import android.support.annotation.NonNull;

import com.hernanbosqued.olx.domain.model.StatusModel;

import java.util.List;

class MainContract {
    public interface View {
        void showItems(@NonNull List<StatusModel> model);
        void showMessage(@NonNull String message);
        void showProgress();
        void hideProgress();
        void showEmpty();
        void hideEmpty();
        void scrollToTop();
    }

    @SuppressWarnings("unused")
    public interface Presenter {
        void processQuery(@NonNull String query);
    }
}
