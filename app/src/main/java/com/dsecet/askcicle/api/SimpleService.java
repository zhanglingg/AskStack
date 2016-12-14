package com.dsecet.askcicle.api;

import com.askcicle.library.rx.retrofit.HttpResult;

import java.util.List;


import retrofit2.http.GET;
import rx.Observable;


/**
 * Created by lin
 * Date:2016/8/3
 * Time:9:28
 */
public interface SimpleService {

    String BASE_URL = "http://www.gank.io/api/";

    /**
     * 获取发布干货的日期
     *
     * @return
     */
    @GET("day/history")
    Observable<HttpResult<List<String>>> getRecentlyDate();

}
