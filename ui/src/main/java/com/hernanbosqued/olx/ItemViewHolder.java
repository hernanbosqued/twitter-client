package com.hernanbosqued.olx;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.hernanbosqued.olx.domain.TwitterModel;

public class ItemViewHolder extends BaseViewHolder<TwitterModel.StatusModel> {
    private TextView textView;
    private TwitterModel.StatusModel item;

    ItemViewHolder(View view, ItemsAdapter.ITEM_TYPE itemType) {
        super(view);
        int color;

        this.textView = (TextView) view.findViewById(R.id.title);
        switch (itemType){
            case ROW_ODD:
                color = ContextCompat.getColor(view.getContext(), R.color.colorPrimaryDark);
                break;
            default:
            case ROW_EVEN:
                color = ContextCompat.getColor(view.getContext(), R.color.colorPrimary);
                break;
        }
        this.textView.setBackgroundColor(color);
    }

    @Override
    public void bind(final TwitterModel.StatusModel item) {
        this.item = item;
        this.textView.setText(item.text);
    }
}
