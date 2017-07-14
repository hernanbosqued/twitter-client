package com.hernanbosqued.olx;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<M> extends RecyclerView.ViewHolder{

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    abstract void bind(M section);
}
