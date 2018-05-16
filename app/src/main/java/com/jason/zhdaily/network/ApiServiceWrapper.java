package com.jason.zhdaily.network;

import com.jason.zhdaily.domain.BaseRequest;
import com.jason.zhdaily.domain.Latest;
import com.jason.zhdaily.domain.News;
import com.jason.zhdaily.domain.StoryExtra;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 *  对请求做统一变换，比如schedulersTransformer
 */
public class ApiServiceWrapper implements ApiService{

    private ApiService apiService = null;
    private static ApiServiceWrapper instance = null;
    private ObservableTransformer schedulersTransformer = null;

    private ApiServiceWrapper() {
        apiService = Api.getApiService();
        schedulersTransformer = new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static ApiServiceWrapper getInstance() {
        if (instance == null) {
            synchronized (ApiServiceWrapper.class) {
                instance = new ApiServiceWrapper();
            }
        }

        return instance;
    }

    @Override
    public Observable<Latest> getLatest() {
        return apiService.getLatest().compose(schedulersTransformer);
    }

    @Override
    public Observable<News> getNews(String id) {
        return apiService.getNews(id).compose(schedulersTransformer);
    }

    @Override
    public Observable<StoryExtra> getStoryExtra(String id) {
        return null;
    }

    @Override
    public <T> Observable<T> executeGet(String url, Map<String, String> maps) {
        return null;
    }

    @Override
    public <T> Observable<T> executePost(String url, BaseRequest body) {
        return null;
    }
}
