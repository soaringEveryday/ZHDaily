package com.jason.zhdaily.network;

import com.jason.zhdaily.domain.Latest;
import com.jason.zhdaily.domain.News;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("news/latest")
    Observable<Latest> getLatest();

    @GET("news/{id}")
    Observable<News> getNews(@Path("id") String id);
}
