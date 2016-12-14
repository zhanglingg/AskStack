package com.dsecet.askcicle.universities;

import android.support.annotation.NonNull;
import android.util.Log;

import com.askcicle.library.rx.retrofit.HttpResult;
import com.askcicle.library.rx.retrofit.factory.ServiceFactory;
import com.askcicle.library.utils.ToastUtils;
import com.dsecet.askcicle.api.SimpleService;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.jakewharton.rxbinding.internal.Preconditions.checkNotNull;

/**
 * Created by zl852 on 2016/12/13.
 */

public class UniversityPresenter implements UniversityContract.Presenter {

    private final UniversityContract.View mUniversityView;

    @Override
    public void start() {

    }


    public UniversityPresenter (@NonNull UniversityContract.View statisticsView) {

        mUniversityView = checkNotNull(statisticsView, "UniversityView cannot be null!");

    }


    @Override
    public void loadData() {

        SimpleService simpleService = ServiceFactory.getInstance().createService(SimpleService.class);
        Observable<HttpResult<List<String>>> recentlyDate = simpleService.getRecentlyDate();

        recentlyDate.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())

                .subscribe(new Subscriber<HttpResult<List<String>>>() {
            @Override
            public void onCompleted() {
                ToastUtils.getInstance().showToast("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                ToastUtils.getInstance().showToast("onError");
            }

            @Override
            public void onNext(HttpResult<List<String>> listHttpResult) {

                List<String> strList = listHttpResult.results;

                for (String srt: strList) {
                    Log.e("str : ", srt);
                }

            }
        });
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void LoadeMore() {

    }
}
