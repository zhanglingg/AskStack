
package com.askcicle.library.rx.retrofit;

import com.askcicle.library.rx.retrofit.factory.ServiceFactory;
import com.askcicle.library.rx.retrofit.func.ResultFunc;
import com.askcicle.library.rx.retrofit.func.RetryWhenNetworkException;
import com.askcicle.library.rx.retrofit.func.StringFunc;
import com.askcicle.library.rx.retrofit.service.CommonService;
import com.askcicle.library.rx.retrofit.subscriber.DownLoadSubscribe;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by lin
 * Date:2016/7/28
 * Time:9:22
 */
public class ObservableProvider {

    private CommonService mCommonService;

    private static class DefaultHolder {
        private static ObservableProvider INSTANCE = new ObservableProvider();
    }

    private ObservableProvider() {
        mCommonService = ServiceFactory.getInstance().createService(CommonService.class);

    }

    public static ObservableProvider getDefault() {
        return DefaultHolder.INSTANCE;
    }

    public Observable<String> loadString(String url) {
        return mCommonService
                .loadString(url)
                .compose(TransformUtils.<ResponseBody>defaultSchedulers())
                .retryWhen(new RetryWhenNetworkException())
                .map(new StringFunc());
    }

    public <T> Observable<HttpResult<T>> loadResult(String url) {
        return loadString(url).map(new ResultFunc<T>());
    }

    public void download(String url, final DownLoadSubscribe subscribe) {
        mCommonService
                .download(url)
                .compose(TransformUtils.<ResponseBody>all_io())
                .doOnNext(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        subscribe.writeResponseBodyToDisk(responseBody);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        subscribe.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscribe.onError(e);
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        //do nothing
                    }
                });

    }


}
