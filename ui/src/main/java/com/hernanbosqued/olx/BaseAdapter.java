package com.hernanbosqued.olx;

import android.support.v7.widget.RecyclerView;

import java.util.List;


public abstract class BaseAdapter<M,VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<M> entities;

    public void setData( List<M> entities){
        this.entities = entities;
        notifyDataSetChanged();
    }

    public boolean hasData( ){
        return entities != null;
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
