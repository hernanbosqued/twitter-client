package com.hernanbosqued.olx;

import com.hernanbosqued.olx.domain.model.EntitiesModel;

public class ItemContract {
    public interface View {
        void setBackground();

        void initViews();

        void setAvatar(String url);

        void showStatus(String text, EntitiesModel.EntityModel[]... entities);

        void setAttachedImage(String mediaUrl);

        void showHeader(String header);
    }
}
