package com.example.administrator.os_china.utils;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.administrator.os_china.App;
import com.example.administrator.os_china.R;
import com.example.administrator.os_china.base.BaseFragment;

/**
 * Created by Administrator on 2017/5/14 0014.
 * <p>
 * 用于Fragment切换跳转 Activity跳转Fragment、Fragment跳转Fragment、普通类跳转Fragment
 */

public class FragmentBuilder {

    private BaseFragment lastFragment;
    private int containerViewId;

    private static FragmentBuilder builder;

    private FragmentBuilder() {
    }

    ;

    public static synchronized FragmentBuilder getInstance() {
        if (builder == null) {
            builder = new FragmentBuilder();
        }

        return builder;
    }

    public FragmentBuilder start(Class<? extends BaseFragment> fragmentClass) {


        FragmentManager fragmentManager = App.activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        String simpleName = fragmentClass.getSimpleName();
        BaseFragment fragment = (BaseFragment) fragmentManager.findFragmentByTag(simpleName);

        if (fragment == null) {
            try {
                //动态加载Fragment
                fragment = fragmentClass.newInstance();
                fragmentTransaction.add(R.id.contentGroup, fragment, simpleName);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        //隐藏上一个Fragment
        if (lastFragment != null)
            fragmentTransaction.hide(lastFragment);

        //显示当前Fragment
        fragmentTransaction.show(fragment);
        fragmentTransaction.addToBackStack(simpleName);
        lastFragment = fragment;
        fragmentTransaction.commit();
        return this;
    }

    public BaseFragment getLastFragment() {
        return lastFragment;
    }

    public void setLastFragment(BaseFragment lastFragment) {
        this.lastFragment = lastFragment;
    }
}
