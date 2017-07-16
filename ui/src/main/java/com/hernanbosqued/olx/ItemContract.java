package com.hernanbosqued.olx;

import com.hernanbosqued.olx.domain.model.EntitiesModel;

class ItemContract {
    public interface View {
        void setBackground();

        void initViews();

        void setAvatar(String url);

        void showStatus(String status, int startIndex, int finishIndex, EntitiesModel.EntityModel[]... entities);

        void setAttachedImage(String mediaUrl);

        void showHeader(String header);

        String getString(int resource);
    }
}
