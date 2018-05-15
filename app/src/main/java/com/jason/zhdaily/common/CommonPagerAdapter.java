package com.jason.zhdaily.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class CommonPagerAdapter<T> extends android.support.v4.view.PagerAdapter {

    private List<T> data;
    private int itemLayout;
    private Context context;
    private int mChildCount = 0;

    public CommonPagerAdapter(List<T> data, int itemLayout, Context context) {
        this.data = data;
        this.itemLayout = itemLayout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(itemLayout, null);
        if (data.size() > 0) {
            convert(view, data.get(getPosition(position)), getPosition(position));
        } else {
            convert(view, null, 0);
        }
        container.addView(view);
        return view;
    }

    private int getPosition(int position) {
        if (position == 0) {
            return 1;
        } else {
            return position % data.size();
        }
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    /**
     * 解决pageadapter刷新无效的问题
     */
    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        if (mChildCount > 0) {
            mChildCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    protected abstract void convert(View view, T t, int position);
}
