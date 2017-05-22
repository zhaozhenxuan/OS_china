package com.example.administrator.os_china.utils;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.administrator.os_china.App;
import com.example.administrator.os_china.R;
import com.example.administrator.os_china.base.BaseFragment;

import static android.R.attr.isDefault;


/**
 * Created by Zhang on 2017/5/13.
 * 用于Fragment切换跳转 Activity跳转Fragment、Fragment跳转Fragment、普通类跳转Fragment
 */

public class FragmentBuilder {

    private static FragmentBuilder builder;
    private int containerViewId;
    private BaseFragment lastFragment;
    private BaseFragment fragment;
    private FragmentTransaction transaction;
    private boolean isDefault = true;
    private boolean isBack = true;
    private String simpleName;
    private int a = 0;

    private FragmentBuilder() {
    }

    public static FragmentBuilder getInstance() {
        if (builder == null) {
            synchronized (FragmentBuilder.class) {
                if (builder == null) {
                    builder = new FragmentBuilder();
                }
            }
        }
        return builder;
    }

    public FragmentBuilder setViewId(int viewId) {
        if (viewId != 0) {
            containerViewId = viewId;
        }
        return this;
    }


    public FragmentBuilder containerId(int containerViewId) {
        this.containerViewId = containerViewId;
        return this;
    }


    public FragmentBuilder start(Class<? extends BaseFragment> fragmentClass) {

        FragmentManager fragmentManager = App.activity.getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        simpleName = fragmentClass.getSimpleName();
        fragment = (BaseFragment) fragmentManager.findFragmentByTag(simpleName);
        if (fragment == null) {
            try {
                //Java动态代理创建对象
                fragment = fragmentClass.newInstance();
                transaction.add(containerViewId, fragment, simpleName);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        /*//隐藏上一个fragment
        if (lastFragment != null)
            transaction.hide(lastFragment);

        //显示当前的Fragment
        transaction.show(fragment);
        transaction.addToBackStack(simpleName);
        lastFragment = fragment;
        transaction.commit();*/


        return this;
    }

    //隐藏上一个fragment
    public FragmentBuilder show() {
        if (isBack) {
            Log.d("FragmentBuilder", "lastFragment = " + lastFragment);
            //隐藏上一个fragment
            if (lastFragment != null)
                transaction.hide(lastFragment);

        }
        return this;
    }


    //替换上一个fragment
    public FragmentBuilder replace() {
        isDefault = false;
        transaction.replace(containerViewId, fragment, simpleName);
        return this;
    }


    //传递参数
    public FragmentBuilder params(Bundle bundle) {
        if (bundle != null)
            fragment.setParams(bundle);
        return this;
    }

    public BaseFragment getLastFragment() {
        return lastFragment;
    }

    public void setLastFragment(BaseFragment lastFragment) {
        this.lastFragment = lastFragment;
    }

    public FragmentBuilder isBack(boolean isBack) {
        this.isBack = isBack;
        if (isBack) {
            a = -1;
            //添加fragment到回退栈
            transaction.addToBackStack(simpleName);
        }
        return this;
    }

    public BaseFragment build() {
        if (isDefault) {
            show();
        }
        //有bug
        if (isBack && a != -1) {
            isBack(true);
        }
        Log.d("FragmentBuilder", "fragment = " + fragment);
        //显示当前的Fragment
        transaction.show(fragment);
        transaction.commit();
        if (isBack)
            lastFragment = fragment;
        isDefault = true;
        isBack = true;
        a = 0;
        return fragment;
    }


}
