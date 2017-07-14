package com.hernanbosqued.olx;

import android.view.View;
import android.widget.TextView;

import com.hernanbosqued.olx.domain.TwitterModel;

public class ItemViewHolder extends BaseViewHolder<TwitterModel.StatusModel> {
    private TextView textView;
    private TwitterModel.StatusModel item;

    ItemViewHolder(View view ) {
        super(view);
        this.textView = (TextView) view.findViewById(R.id.title);
    }

    @Override
    public void bind(final TwitterModel.StatusModel item) {
        this.item = item;
        this.textView.setText(item.text);
    }
}
