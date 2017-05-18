package com.example.administrator.os_china.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/14 0014.
 */

public class Synthetical_ViewPagerAdapter extends FragmentPagerAdapter {

    String[] name = new String[]{"开源资讯", "推荐博客", "技术问答", "每日一博"};
    private ArrayList<Fragment> list;

    public Synthetical_ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
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
