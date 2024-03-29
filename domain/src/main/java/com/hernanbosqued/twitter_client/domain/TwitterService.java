package com.hernanbosqued.twitter_client.domain;

import com.hernanbosqued.twitter_client.domain.model.ErrorModel;
import com.hernanbosqued.twitter_client.domain.model.StatusModel;
import com.hernanbosqued.twitter_client.domain.model.TwitterModel;

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
