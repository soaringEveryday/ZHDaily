package com.jason.zhdaily.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
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
import com.jason.zhdaily.common.CommonBannerAdapter;
import com.jason.zhdaily.common.HeaderWrapper;
import com.jason.zhdaily.domain.Latest;
import com.jason.zhdaily.domain.StoryExtra;
import com.jason.zhdaily.network.Api;
import com.jason.zhdaily.network.ApiServiceWrapper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.jason.zhdaily.view.NewsFragment.KEY_ID;

public class LatestFragment extends BaseFragment {

    protected RecyclerView mRecyclerView;
    protected CommonAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected HeaderWrapper mHeaderAdapter;
    protected List<Latest.StoriesBean> mLatestStoryData = new ArrayList<>();
    protected List<Latest.TopStoriesBean> mLatestTopData = new ArrayList<>();
    protected ViewPager mBanner = null;
    protected CommonBannerAdapter mCommonPagerAdapter;
    protected SparseArrayCompat<StoryExtra> mExtras = new SparseArrayCompat<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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

            @Override
            public void itemClick(View view, Latest.StoriesBean item, int position) {
                Latest.StoriesBean story = mLatestStoryData.get(position);
                if (story != null) {
                    Intent intent = new Intent(getActivity(), NewsActivity.class);
                    intent.putExtra(KEY_ID, story.getId());
                    startActivity(intent);
                }
            }
        };
        mHeaderAdapter = new HeaderWrapper(mAdapter);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mHeaderAdapter);
        requestLatestNews();

        return rootView;
    }

    private void requestLatestNews() {
//        Api.getApiService().getLatest()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Latest>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        addDisposable(d);
//                    }
//
//                    @Override
//                    public void onNext(Latest latest) {
//                        mLatestStoryData.addAll(latest.getStories());
//                        mHeaderAdapter.notifyDataSetChanged();
//                        mLatestTopData.addAll(latest.getTop_stories());
//                        updateBanner();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });


        ApiServiceWrapper.getInstance().getLatest().subscribe(new Observer<Latest>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(Latest latest) {
                mLatestStoryData.addAll(latest.getStories());
                mHeaderAdapter.notifyDataSetChanged();
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


        View bannerView = LayoutInflater.from(getContext()).inflate(R.layout.include_banner, null, false);
        mBanner = bannerView.findViewById(R.id.vp_banner);
        mCommonPagerAdapter = new CommonBannerAdapter<Latest.TopStoriesBean>(mLatestTopData, R.layout.item_banner, CustomApplication.getContext()) {
            @Override
            protected void convert(View view, Latest.TopStoriesBean item, int position) {
                if (item != null) {
                    TextView title = view.findViewById(R.id.tv_item_banner_title);
                    ImageView image = view.findViewById(R.id.iv_item_banner_image);
                    Glide.with(CustomApplication.getContext()).load(item.getImage()).into(image);
                    title.setText(item.getTitle());
                }
            }

            @Override
            protected void itemClick(View view, Latest.TopStoriesBean topStoriesBean, int position) {
                Latest.TopStoriesBean story = mLatestTopData.get(position);
                if (story != null) {
                    Intent intent = new Intent(getActivity(), NewsActivity.class);
                    intent.putExtra(KEY_ID, story.getId());
                    startActivity(intent);
                }
            }
        };
        mBanner.setAdapter(mCommonPagerAdapter);

        mHeaderAdapter.addHeaderView(bannerView);
        mHeaderAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
//        test();
    }

    private void test() {

        Api.getApiService().getLatest()
                .flatMap(new Function<Latest, ObservableSource<List<Latest.StoriesBean>>>() {
                    @Override
                    public ObservableSource<List<Latest.StoriesBean>> apply(Latest latest) {
                        mLatestStoryData.addAll(latest.getStories());
                        return Observable.fromArray(latest.getStories());
                    }
                })
                .flatMap(new Function<List<Latest.StoriesBean>, ObservableSource<Latest.StoriesBean>>() {
                    @Override
                    public ObservableSource<Latest.StoriesBean> apply(List<Latest.StoriesBean> storiesBeans) {
                        return Observable.fromIterable(storiesBeans);
                    }
                })
                .flatMap(new Function<Latest.StoriesBean, Observable<StoryExtra>>() {
                    @Override
                    public Observable<StoryExtra> apply(Latest.StoriesBean storiesBean) {
                        return Api.getApiService().getStoryExtra(String.valueOf(storiesBean.getId()));
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoryExtra>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(StoryExtra storyExtra) {

                        if (storyExtra != null) {
                            mExtras.append(0, storyExtra);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.print("finished");
                    }
                });

    }

}
