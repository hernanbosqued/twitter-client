package com.hernanbosqued.olx;

import android.support.v7.widget.RecyclerView;
import android.view.View;

abstract class BaseViewHolder<M> extends RecyclerView.ViewHolder{

    BaseViewHolder(View itemView) {
        super(itemView);
    }

    abstract void bind(M section);
}
