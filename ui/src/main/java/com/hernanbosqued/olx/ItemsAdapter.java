package com.hernanbosqued.olx;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hernanbosqued.olx.domain.TwitterModel;

class ItemsAdapter extends BaseAdapter<TwitterModel.StatusModel, ItemViewHolder> {

    enum ITEM_TYPE {
        ROW_EVEN(0),
        ROW_ODD(1);

        private int itemType;

        ITEM_TYPE(int itemType) {
            this.itemType = itemType;
        }

        public static ITEM_TYPE valueOf(int itemType) {
            for (ITEM_TYPE item : ITEM_TYPE.values()) {
                if (item.itemType == itemType) {
                    return item;
                }
            }
            return ROW_EVEN;
        }
    }

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
