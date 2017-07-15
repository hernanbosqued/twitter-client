package com.hernanbosqued.olx;

import com.hernanbosqued.olx.domain.model.StatusModel;

import java.util.List;

public class MainContract {
    public interface View {
        void showItems(List<StatusModel> model);
        void showMessage(String message);
        void showProgress();
        void hideProgress();
        void showEmpty();
        void hideEmpty();
        void scrollToTop();
    }

    public interface Presenter {
        void processQuery(String query);
    }
}
