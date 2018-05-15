package com.jason.zhdaily.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.jason.zhdaily.R;
import com.jason.zhdaily.domain.News;
import com.jason.zhdaily.network.Api;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsFragment extends BaseFragment {

    public static final String KEY_ID = "key_id";
    private String mNewsId = "";
    private WebView mWeb;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNewsId = String.valueOf(getArguments().getInt(KEY_ID));
        }
        setBackVisible();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_news, container, false);
        mWeb = rootView.findViewById(R.id.webview);
        settingWebView(mWeb);
        requestNewsDetail();
        return rootView;
    }

    private void requestNewsDetail() {
        Api.getApiService().getNews(mNewsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<News>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(News news) {
                        mWeb.loadUrl(news.getShare_url());
                        setTitle(news.getTitle());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void settingWebView(WebView webview) {
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
    }
}
