package com.hernanbosqued.twitter_client;

import android.support.annotation.NonNull;

import com.hernanbosqued.twitter_client.domain.model.StatusModel;
import com.hernanbosqued.twitter_client.domain.TwitterService;

import java.util.List;

import com.hernanbosqued.twitter_client.repo.TwitterRepositotyImpl;

public class MainPresenter extends BasePresenter<List<StatusModel>, MainContract.View> implements TwitterService.Callbacks, MainContract.Presenter {

    private final TwitterService service;
    private boolean isLoading;
    private boolean isEmpty;

    MainPresenter() {
        service = new TwitterService(TwitterRepositotyImpl.getInstance());
    }

    @Override
    public void processQuery(@NonNull String query) {
        isLoading = true;
        view().showProgress();
        service.getTweets(query, this);
    }

    @Override
    public void onStatusesReceived(@NonNull List<StatusModel> statuses) {
        isLoading = false;
        setModel(statuses);
        view().hideProgress();

        if (statuses.isEmpty()) {
            isEmpty = true;
            view().showEmpty();
        } else {
            isEmpty = false;
            view().hideEmpty();
            view().scrollToTop();
        }
    }

    @Override
    public void onServiceError(@NonNull String error) {
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
        if (isEmpty) {
            view().showEmpty();
        }
        if (isLoading) {
            view().showProgress();
        }
        if (model != null && !model.isEmpty()) {
            view().showItems(model);
        }
    }
}
