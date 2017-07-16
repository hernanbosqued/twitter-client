package com.hernanbosqued.olx.domain;

import com.hernanbosqued.olx.domain.model.ErrorModel;
import com.hernanbosqued.olx.domain.model.StatusModel;
import com.hernanbosqued.olx.domain.model.TwitterModel;

import java.util.List;

public class TwitterService {
    private final TwitterRepository repo;

    public TwitterService(TwitterRepository repo){
        this.repo = repo;
    }

    public interface Callbacks {
        void onStatusesReceived( List<StatusModel> model );
        void onServiceError(String error );
    }

    public void getTweets(String query, final Callbacks callbacks ){
        repo.getTweets(query, new ModelCallbacks<TwitterModel>() {
            @Override
            public void onSuccess(TwitterModel data) {
                callbacks.onStatusesReceived(data.statuses);
            }

            @Override
            public void onFail(ErrorModel errorModel) {
                callbacks.onServiceError(errorModel.message);
            }
        });
    }
}
