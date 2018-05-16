package com.jason.zhdaily.network;

import com.jason.zhdaily.domain.BaseRequest;
import com.jason.zhdaily.domain.Latest;
import com.jason.zhdaily.domain.News;
import com.jason.zhdaily.domain.StoryExtra;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * https://github.com/izzyleung/ZhihuDailyPurify/wiki/%E7%9F%A5%E4%B9%8E%E6%97%A5%E6%8A%A5-API-%E5%88%86%E6%9E%90
 */
public interface ApiService {

    /**
     * 常量接口模式
     * https://blog.csdn.net/voo00oov/article/details/50433672
     */
    String baseUrl = "https://news-at.zhihu.com/api/4/";

    @GET("news/latest")
    Observable<Latest> getLatest();

    @GET("news/{id}")
    Observable<News> getNews(@Path("id") String id);

    @GET("story-extra/{id}")
    Observable<StoryExtra> getStoryExtra(@Path("id") String id);

    /**
     * 通用get方法，貌似行不通，T要用确定的类型，比如BaseResponse, Retrofit需要在运行时知道类型以反序列化
     * @param url
     * @param maps
     * @return
     */
    @GET("{url}")
    <T> Observable<T> executeGet(@Path("url") String url, @QueryMap Map<String, String> maps);

    /**
     * 通用Post方法
     * @param url
     * @param body
     * @return
     */
    @POST("{url}")
    <T> Observable<T> executePost(@Path("url") String url, @Body BaseRequest body);
}
