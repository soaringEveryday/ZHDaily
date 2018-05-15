package com.jason.zhdaily.common;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class HeaderWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BASE_ITEM_TYPE_HEADER = 100000;

    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();

    private CommonAdapter mInnerAdapter;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if (mHeaderViews.get(viewType) != null)
        {

//            return ViewHolder.createViewHolder(parent.getContext(), mHeaderViews.get(viewType));
            View view = mHeaderViews.get(viewType);
            return new CommonAdapter.ViewHolder(view);

        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position)
    {
        if (isHeaderViewPos(position))
        {
            return mHeaderViews.keyAt(position);
        }
        return mInnerAdapter.getItemViewType(position - getHeadersCount());
    }

    private int getRealItemCount()
    {
        return mInnerAdapter.getItemCount();
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        if (isHeaderViewPos(position))
        {
            return;
        }
        mInnerAdapter.onBindViewHolder((CommonAdapter.ViewHolder) holder, position - getHeadersCount());
    }

    @Override
    public int getItemCount()
    {
        return getHeadersCount() + getRealItemCount();
    }

    public HeaderWrapper(CommonAdapter adapter)
    {
        mInnerAdapter = adapter;
    }

    private boolean isHeaderViewPos(int position)
    {
        return position < getHeadersCount();
    }


    public void addHeaderView(View view)
    {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }


    public int getHeadersCount()
    {
        return mHeaderViews.size();
    }
}
