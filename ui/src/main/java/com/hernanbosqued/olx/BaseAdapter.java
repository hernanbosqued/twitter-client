package com.hernanbosqued.olx;

import android.support.v7.widget.RecyclerView;

import java.util.List;


public abstract class BaseAdapter<M,VH extends BaseViewHolder<M>> extends RecyclerView.Adapter<VH> {

    private List<M> entities;

    public void setData( List<M> entities){
        this.entities = entities;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bind(entities.get(position));
    }

    @Override
    public int getItemCount() {
        return entities != null ? entities.size() : 0;
    }
}
