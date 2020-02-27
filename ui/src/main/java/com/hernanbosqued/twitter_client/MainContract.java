package com.hernanbosqued.twitter_client;

import android.support.annotation.NonNull;

import com.hernanbosqued.twitter_client.domain.model.StatusModel;

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
