package com.hernanbosqued.twitter_client;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hernanbosqued.twitter_client.domain.model.StatusModel;

class ItemsAdapter extends BaseAdapter<StatusModel, ItemViewHolder> {

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new ItemViewHolder(view, ITEM_TYPE.valueOf(viewType));
    }
}
