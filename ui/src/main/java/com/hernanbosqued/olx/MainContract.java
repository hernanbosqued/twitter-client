package com.hernanbosqued.olx;

import com.hernanbosqued.olx.domain.TwitterModel;

import java.util.List;

public class MainContract {
    public interface View {
        void showItems(List<TwitterModel.StatusModel> model);
        void showMessage(String message);
        void showProgress();
        void hideProgress();
    }

    public interface Presenter {
        void processQuery(String query);
    }
}
