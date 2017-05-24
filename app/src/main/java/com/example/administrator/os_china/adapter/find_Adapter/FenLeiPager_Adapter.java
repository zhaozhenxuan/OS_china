package com.example.administrator.os_china.adapter.find_Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class FenLeiPager_Adapter extends FragmentPagerAdapter {

    String[] name = new String[]{"分类", "推荐", "最新", "热门","国产"};
    private ArrayList<Fragment> list;

    public FenLeiPager_Adapter(FragmentManager fm , ArrayList<Fragment> list) {
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
