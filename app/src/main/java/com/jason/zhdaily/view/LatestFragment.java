package com.jason.zhdaily.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jason.zhdaily.CustomApplication;
import com.jason.zhdaily.R;
import com.jason.zhdaily.common.CommonAdapter;
import com.jason.zhdaily.domain.Latest;
import com.jason.zhdaily.network.Api;
import com.jason.zhdaily.common.CommonPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LatestFragment extends BaseFragment {

    protected RecyclerView mRecyclerView;
    protected CommonAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Latest.StoriesBean> mLatestStoryData = new ArrayList<>();
    protected List<Latest.TopStoriesBean> mLatestTopData = new ArrayList<>();
    protected ViewPager mBanner = null;
    protected CommonPagerAdapter mCommonPagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_latest, container, false);
        mRecyclerView = rootView.findViewById(R.id.rv_latest);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new CommonAdapter<Latest.StoriesBean>(mLatestStoryData, R.layout.item_latest) {

            @Override
            public void convert(ViewHolder helper, Latest.StoriesBean item, int position) {
                ImageView image = helper.itemView.findViewById(R.id.iv_item_latest_image);
                TextView title = helper.itemView.findViewById(R.id.tv_item_latest_title);
                Glide.with(CustomApplication.getContext()).load(item.getImages().get(0)).into(image);
                title.setText(item.getTitle());
            }
        };
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        requestLatestNews();

        mBanner = rootView.findViewById(R.id.vp_banner);
        return rootView;
    }

    private void requestLatestNews() {
        Api.getApiService().getLatest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Latest>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(Latest latest) {
                        mLatestStoryData.addAll(latest.getStories());
                        mAdapter.notifyDataSetChanged();
                        mLatestTopData.addAll(latest.getTop_stories());
                        updateBanner();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void updateBanner() {
        mBanner.setCurrentItem(0);
        mCommonPagerAdapter = new CommonPagerAdapter<Latest.TopStoriesBean>(mLatestTopData, R.layout.item_banner, CustomApplication.getContext()) {
            @Override
            protected void convert(View view, Latest.TopStoriesBean item, int position) {
                if (item != null) {
                    TextView title = view.findViewById(R.id.tv_item_banner_title);
                    ImageView image = view.findViewById(R.id.iv_item_banner_image);
                    Glide.with(CustomApplication.getContext()).load(item.getImage()).into(image);
                    title.setText(item.getTitle());
                }
            }
        };
        mBanner.setAdapter(mCommonPagerAdapter);

    }
}
