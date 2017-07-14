package com.hernanbosqued.olx;

import android.support.annotation.NonNull;

import com.hernanbosqued.olx.domain.TwitterModel;
import com.hernanbosqued.olx.domain.TwitterService;

import java.util.List;

import repo.olx.hernanbosqued.com.repo.TwitterRepositotyImpl;

public class MainPresenter extends BasePresenter<List<TwitterModel.StatusModel>, MainContract.View> implements TwitterService.Callbacks, MainContract.Presenter {

    private TwitterService service;
    private boolean isLoading;

    MainPresenter( ) {
        service = new TwitterService(TwitterRepositotyImpl.getInstance());
    }

    @Override
    public void processQuery(String query) {
        isLoading = true;
        view().showProgress();
        service.getTweets(query, this);
    }

    @Override
    public void onStatusesReceived(List<TwitterModel.StatusModel> statuses) {
        isLoading = false;
        setModel(statuses);
        view().hideProgress();
    }

    @Override
    public void onServiceError(String error) {
        isLoading = false;
        view().showMessage(error);
        view().hideProgress();
    }

    @Override
    protected void updateView() {
        view().showItems(model);
    }

    @Override
    public void bindView(@NonNull MainContract.View view) {
        super.bindView(view);
        if (isLoading) {
            view().showProgress();
        }
        if (model != null && !model.isEmpty()) {
            view().showItems(model);
        }
    }
}
