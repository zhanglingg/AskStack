package com.askcicle.library.rx.retrofit.func;


import com.askcicle.library.rx.retrofit.HttpResult;
import com.askcicle.library.utils.json.JsonConvert;

import rx.functions.Func1;

/**
 * Created by lin
 * Date:2016/7/28
 * Time:11:04
 */
public class ResultFunc<T> implements Func1<String, HttpResult<T>> {
    @Override
    public HttpResult<T> call(String result) {
        JsonConvert<HttpResult<T>> convert = new JsonConvert<HttpResult<T>>() {
        };
        return convert.parseData(result);
    }
}
