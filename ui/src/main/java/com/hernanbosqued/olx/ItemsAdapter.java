package com.hernanbosqued.olx;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hernanbosqued.olx.domain.TwitterModel;

class ItemsAdapter extends BaseAdapter<TwitterModel.StatusModel, ItemViewHolder> {

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout;
//        switch (type) {
//            case POSTER:
//                layout = R.layout.layout_poster;
//                break;
//            case THUMB:
//                layout = R.layout.layout_thumb;
//                break;
//            default:
//                //error layout here
//                layout = 0;
//                break;
//        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new ItemViewHolder(view);
    }
}
