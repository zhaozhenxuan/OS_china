package com.example.administrator.os_china.utils;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.administrator.os_china.App;
import com.example.administrator.os_china.R;
import com.example.administrator.os_china.base.BaseFragment;


/**
 * Created by Zhang on 2017/5/13.
 * 用于Fragment切换跳转 Activity跳转Fragment、Fragment跳转Fragment、普通类跳转Fragment
 */

public class FragmentBuilder {

    private static FragmentBuilder builder;
    private int containerViewId = R.id.contentGroup;
    private BaseFragment lastFragment;
    private BaseFragment fragment;

    private FragmentBuilder(){}
    public static FragmentBuilder getInstance(){
        if (builder == null){
            synchronized (FragmentBuilder.class){
                if (builder == null){
                    builder = new FragmentBuilder();
                }
            }
        }
        return builder;
    }

    public FragmentBuilder setViewId(int viewId){
        if (viewId!=0){
            containerViewId = viewId;
        }
        return this;
    }

    public FragmentBuilder start(Class<? extends BaseFragment> fragmentClass){

        FragmentManager fragmentManager = App.activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        String simpleName = fragmentClass.getSimpleName();
        fragment = (BaseFragment) fragmentManager.findFragmentByTag(simpleName);
        if(fragment == null){
            try {
                //Java动态代理创建对象
                fragment = fragmentClass.newInstance();
                transaction.add(containerViewId,fragment,simpleName);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        //隐藏上一个fragment
        if (lastFragment != null)
            transaction.hide(lastFragment);

        //显示当前的Fragment
        transaction.show(fragment);
        transaction.addToBackStack(simpleName);
        lastFragment = fragment;
        transaction.commit();
        return this;
    }
    public BaseFragment getLastFragment() {
        return lastFragment;
    }

    public void setParams(Bundle params){
        fragment.setParams(params);
    }

    public void setLastFragment(BaseFragment lastFragment) {
        this.lastFragment = lastFragment;
    }
}
