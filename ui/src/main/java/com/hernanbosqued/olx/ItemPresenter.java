package com.hernanbosqued.olx;

import android.support.annotation.NonNull;

import com.hernanbosqued.olx.domain.Utils;
import com.hernanbosqued.olx.domain.model.MediaEntityModel;
import com.hernanbosqued.olx.domain.model.StatusModel;

class ItemPresenter extends BasePresenter<StatusModel, ItemContract.View> {

    private boolean isRetweet;

    void bind(@NonNull ItemContract.View view, @NonNull StatusModel model) {
        super.bindView(view);
        setModel(model);
    }

    @Override
    protected void updateView() {
        view().initViews();
        view().setBackground();
        view().setAvatar(model.user.profileImageUrl);
        StatusModel status;
        String screenName = "@" + model.user.screenName;
        String retweetScreenName = "";
        if( model.retweetedStatus != null ){
            isRetweet = true;
            retweetScreenName = "RT from @" +model.retweetedStatus.user.screenName;
            status = model.retweetedStatus;
        }else{
            status = model;
        }
        view().showHeader(screenName + " " + retweetScreenName);
        view().showStatus(status.text,
                status.displayTextRange[0],
                status.displayTextRange[1],
                status.entities.hashtags,status.entities.urls, status.entities.userMentions);
        setMediaUrl();
    }

    private void setMediaUrl() {
        String mediaUrl = getMediaUrl();
        if (!Utils.isNullOrEmpty(mediaUrl)) {
            view().setAttachedImage(mediaUrl);
        }
    }

    private String getMediaUrl() {
        if (model.extendedEntities != null && model.extendedEntities.media != null) {
            for (MediaEntityModel item : model.extendedEntities.media) {
                if (item.type.contentEquals("photo")) {
                    return item.mediaUrl;
                }
            }
        }
        return null;
    }
}
