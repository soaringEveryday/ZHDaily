package com.jason.zhdaily.network;

import com.jason.zhdaily.domain.Latest;
import com.jason.zhdaily.domain.News;
import com.jason.zhdaily.domain.StoryExtra;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * https://github.com/izzyleung/ZhihuDailyPurify/wiki/%E7%9F%A5%E4%B9%8E%E6%97%A5%E6%8A%A5-API-%E5%88%86%E6%9E%90
 */
public interface ApiService {

    @GET("news/latest")
    Observable<Latest> getLatest();

    @GET("news/{id}")
    Observable<News> getNews(@Path("id") String id);

    @GET("story-extra/{id}")
    Observable<StoryExtra> getStoryExtra(@Path("id") String id);
}
