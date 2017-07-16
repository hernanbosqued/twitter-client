package com.hernanbosqued.olx;

import android.support.annotation.NonNull;

import com.hernanbosqued.olx.domain.model.EntitiesModel;

class ItemContract {
    public interface View {
        void setBackground();

        void initViews();

        void setAvatar(@NonNull String url);

        void showStatus(@NonNull String status, int startIndex, int finishIndex, @NonNull EntitiesModel.EntityModel[]... entities);

        void setAttachedImage(@NonNull String mediaUrl);

        void showHeader(@NonNull String header);

        String getString(int resource);
    }
}
