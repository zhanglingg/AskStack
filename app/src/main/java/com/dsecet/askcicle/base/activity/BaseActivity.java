package com.dsecet.askcicle.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:9:40
 */
public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(savedInstanceState);
        setContentView(setLayoutResourceID());
        setUpView();
        setUpData();
    }



    protected void setUpData() {
    }

    /***
     * 用于在初始化View之前做一些事
     */
    protected void init(Bundle savedInstanceState) {

    }

    protected abstract int setLayoutResourceID();

    protected abstract void setUpView();


    protected <T extends View> T $(int id) {
        return (T) super.findViewById(id);
    }


    protected void startActivityWithoutExtras(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    protected void startActivityWithExtras(Class<?> clazz, Bundle extras) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(extras);
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1:

                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);

    }
}
