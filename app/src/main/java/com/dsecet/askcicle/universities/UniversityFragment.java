package com.dsecet.askcicle.universities;

import com.dsecet.askcicle.R;
import com.dsecet.askcicle.base.fragment.BaseFragment;


public class UniversityFragment extends BaseFragment implements UniversityContract.View {

    private UniversityContract.Presenter mPresenter;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_university;
    }

    @Override
    protected void init() {
        super.init();
        mPresenter = new UniversityPresenter(this);
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpData() {
        mPresenter.loadData();
    }



    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
