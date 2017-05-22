package com.example.administrator.os_china.fragment.home_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.activity.Search_Activity;
import com.example.administrator.os_china.adapter.Trends_ViewPagerAdapter;
import com.example.administrator.os_china.base.BaseFragment;
import com.example.administrator.os_china.fragment.trends_fragment.MeiriDT_Fragment;
import com.example.administrator.os_china.fragment.trends_fragment.MineDT_Fragment;
import com.example.administrator.os_china.fragment.trends_fragment.NewsDT_Fragment;
import com.example.administrator.os_china.fragment.trends_fragment.RemenDT_Fragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/14 0014.
 * 动弹Fragment
 */

public class Trends_fragment extends BaseFragment {

    @BindView(R.id.tv_toobar)
    TextView tvToobar;
    @BindView(R.id.treads_tab)
    TabLayout treadsTab;
    @BindView(R.id.trends_pager)
    ViewPager trendsPager;
    Unbinder unbinder;
    @BindView(R.id.search_btn)
    ImageView searchBtn;

    private ArrayList<Fragment> list;
    private Trends_ViewPagerAdapter pagerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.trends_item;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {
        list = new ArrayList<>();

        MeiriDT_Fragment meiriDTFragment = new MeiriDT_Fragment();
        MineDT_Fragment mineDTFragment = new MineDT_Fragment();
        NewsDT_Fragment newsDTFragment = new NewsDT_Fragment();
        RemenDT_Fragment remenDTFragment = new RemenDT_Fragment();

        list.add(newsDTFragment);
        list.add(remenDTFragment);
        list.add(meiriDTFragment);
        list.add(mineDTFragment);

        pagerAdapter = new Trends_ViewPagerAdapter(getChildFragmentManager(), list);

        //加载多少Fragment
        trendsPager.setOffscreenPageLimit(4);

        trendsPager.setAdapter(pagerAdapter);


        treadsTab.setupWithViewPager(trendsPager);
        treadsTab.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void loderData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.search_btn)
    public void onViewClicked() {

        Intent intent = new Intent(getActivity() , Search_Activity.class);
        startActivity(intent);

    }
}
