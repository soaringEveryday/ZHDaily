package com.jason.zhdaily.common;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonAdapter.ViewHolder> {

    private List<T> mDataSet;
    private final int mItemLayoutId;

    public CommonAdapter(List<T> data, int layoutId) {
        this.mDataSet = data;
        this.mItemLayoutId = layoutId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mItemLayoutId, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (int) view.getTag();
                itemClick(view, mDataSet.get(position), position);
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, mDataSet.get(position), position);
        holder.itemView.setTag(position);// save click position
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public abstract void convert(ViewHolder helper, T item, int position);

    public abstract void itemClick(View view, T item, int position);
}
