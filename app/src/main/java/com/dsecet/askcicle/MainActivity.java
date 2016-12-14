package com.dsecet.askcicle;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.dsecet.askcicle.base.activity.BaseMainActivity;
import com.dsecet.askcicle.base.fragment.BaseFragment;
import com.dsecet.askcicle.expert.ExpertFragment;
import com.dsecet.askcicle.favorite.FavoriteFragment;
import com.dsecet.askcicle.me.MyFragment;
import com.dsecet.askcicle.universities.UniversityFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseMainActivity {

    private  Map<String, BaseFragment> fragmentList = new HashMap<>();
    private BottomNavigationView mBottomNavigationView;
    private FragmentManager mFragmentManager;
    private BaseFragment mCurrentFragment;


    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
        mCurrentFragment = (BaseFragment) mFragmentManager.findFragmentById(R.id.frame_content);
        if (mCurrentFragment == null) {
            mCurrentFragment = createFragment(UniversityFragment.class);
            mFragmentManager.beginTransaction().add(R.id.frame_content, mCurrentFragment).commit();
        }
        FragmentTransaction trans = mFragmentManager.beginTransaction();
        if (null != savedInstanceState) {
            List<Fragment> fragments = mFragmentManager.getFragments();
            for (int i = 0; i < fragments.size(); i++) {
                trans.hide(fragments.get(i));
            }
        }
        trans.show(mCurrentFragment).commitAllowingStateLoss();
    }

    @Override
    protected void setUpView() {

        mBottomNavigationView = $(R.id.bottom_navigation);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Class<?> clazz = null;
                switch (item.getItemId()) {
                    case R.id.university:
                        clazz = UniversityFragment.class;
                        break;
                    case R.id.expert:
                        clazz = ExpertFragment.class;
                        break;
                    case R.id.favorite:
                        clazz = FavoriteFragment.class;
                        break;
                    case R.id.me:
                        clazz = MyFragment.class;
                        break;

                    default:
                        break;
                }
                if (clazz != null) {
                    switchFragment(clazz);
                }
                return true;
            }
        });

    }

    private void switchFragment(Class<?> clazz) {
        if (clazz == null) return;
        BaseFragment to = createFragment(clazz);
        if (to.isAdded()) {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).show(to).commitAllowingStateLoss();
        } else {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).add(R.id.frame_content, to).commitAllowingStateLoss();
        }
        mCurrentFragment = to;

    }

    /**
     * 根据Class创建Fragment
     *
     * @param clazz the Fragment of create
     * @return
     */
    public  BaseFragment createFragment(Class<?> clazz, boolean isObtain) {
        BaseFragment resultFragment = null;
        String className = clazz.getName();
        if (fragmentList.containsKey(className)) {
            resultFragment = fragmentList.get(className);
        } else {
            try {
                try {
                    resultFragment = (BaseFragment) Class.forName(className).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (isObtain)
                fragmentList.put(className, resultFragment);
        }

        return resultFragment;
    }

    public  BaseFragment createFragment(Class<?> clazz) {
        return createFragment(clazz, true);
    }

}
