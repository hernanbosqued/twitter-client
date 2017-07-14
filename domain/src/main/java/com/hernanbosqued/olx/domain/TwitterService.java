package com.hernanbosqued.olx.domain;

import java.util.List;

public class TwitterService {
    private TwitterRepository repo;

    public TwitterService(TwitterRepository repo){
        this.repo = repo;
    }

    public interface Callbacks {
        void onStatusesReceived( List<TwitterModel.StatusModel> model );
        void onServiceError(String error );
    }

    public void getTweets(String query, final Callbacks callbacks ){
        repo.getTweets(query, new ModelCallbacks<TwitterModel>() {
            @Override
            public void onSuccess(TwitterModel data) {
                callbacks.onStatusesReceived(data.statuses);
            }

            @Override
            public void onFail(ErrorModel.Error error) {
                callbacks.onServiceError(error.message);
            }
        });
    }
}
