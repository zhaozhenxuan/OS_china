package com.example.administrator.os_china.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.os_china.model.entity.trends_beans.Trends_Beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class Trends_ViewPagerAdapter extends FragmentPagerAdapter {

    String[] name = new String[]{"最新动弹", "热门动弹", "每日乱弹", "我的动弹"};
    private ArrayList<Fragment> list;

    public Trends_ViewPagerAdapter(FragmentManager fm , ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return name[position];
    }
}
