package com.hernanbosqued.olx;

import android.support.annotation.NonNull;

import com.hernanbosqued.olx.domain.Utils;
import com.hernanbosqued.olx.domain.model.MediaEntityModel;
import com.hernanbosqued.olx.domain.model.StatusModel;

class ItemPresenter extends BasePresenter<StatusModel, ItemContract.View> {

    void bind(@NonNull ItemContract.View view, @NonNull StatusModel model) {
        super.bindView(view);
        setModel(model);
    }

    private StatusModel getStatus() {
        if (model.retweetedStatus != null) {
            return model.retweetedStatus;
        } else {
            return model;
        }
    }

    private String getHeader() {
        String screenName = "@" + model.user.screenName;
        String retweetScreenName = "";
        if (model.retweetedStatus != null) {
            retweetScreenName = "RT " + view().getString(R.string.from) + " @" + model.retweetedStatus.user.screenName;
        }
        return screenName + " " + retweetScreenName;
    }

    @Override
    protected void updateView() {
        view().initViews();
        view().setBackground();
        view().setAvatar(model.user.profileImageUrl);
        StatusModel status = getStatus();
        view().showHeader(getHeader());
        view().showStatus(status.text,
                status.displayTextRange[0],
                status.displayTextRange[1],
                status.entities.hashtags, status.entities.urls, status.entities.userMentions);
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
