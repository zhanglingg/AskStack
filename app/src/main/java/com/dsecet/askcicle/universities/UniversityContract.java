package com.dsecet.askcicle.universities;


import com.dsecet.askcicle.base.BasePresenter;
import com.dsecet.askcicle.base.BaseView;




/**
 * Created by zl852 on 2016/12/13.
 */

public interface UniversityContract {

    interface View extends BaseView<Presenter> {
        void showLoading();
        void hideLoading();

    }

    interface Presenter extends BasePresenter {

        void loadData();
        void refreshData();
        void LoadeMore();
    }
}
