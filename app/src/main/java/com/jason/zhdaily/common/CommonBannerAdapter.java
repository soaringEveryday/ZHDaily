package com.jason.zhdaily.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.List;

public abstract class CommonBannerAdapter<T> extends android.support.v4.view.PagerAdapter {

    private List<T> data;
    private int itemLayout;
    private Context context;
    private int mChildCount = 0;

    private SparseArray<View> views = new SparseArray<>();

    public CommonBannerAdapter(List<T> data, int itemLayout, Context context) {
        this.data = data;
        this.itemLayout = itemLayout;
        this.context = context;
    }

    @Override
    public int getCount() {
//        return data == null || data.isEmpty() ? 0 : data.size() + 2;
        return data == null || data.isEmpty() ? 0 : data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        View view = views.get(position);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(itemLayout, null);
            views.put(position, view);
        }

        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp = view.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(view);
        }

        if (data.size() > 0) {
            convert(view, data.get(position), position);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick(view, data.get(position), position);
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        container.removeViewAt(position);
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
    protected abstract void itemClick(View view, T t, int position);

}
