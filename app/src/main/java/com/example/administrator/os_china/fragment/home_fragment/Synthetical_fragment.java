package com.example.administrator.os_china.fragment.home_fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.adapter.Home_ViewPagerAdapter;
import com.example.administrator.os_china.base.BaseFragment;
import com.example.administrator.os_china.fragment.Synthetical_fragment.Blogs_Fragment;
import com.example.administrator.os_china.fragment.Synthetical_fragment.Exercise_Fragment;
import com.example.administrator.os_china.fragment.Synthetical_fragment.Inquire_Fragment;
import com.example.administrator.os_china.fragment.Synthetical_fragment.Message_Fragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.administrator.os_china.R.id.synthetical_pager;
import static com.example.administrator.os_china.R.id.synthetical_tab;

/**
 * Created by Administrator on 2017/5/14 0014.
 */

public class Synthetical_fragment extends BaseFragment {
    @BindView(R.id.tv_toobar)
    TextView tvToobar;
    @BindView(R.id.synthetical_toolbar)
    Toolbar syntheticalToolbar;
    @BindView(synthetical_tab)
    TabLayout syntheticalTab;
    @BindView(synthetical_pager)
    ViewPager syntheticalPager;
    @BindView(R.id.ptrframelayout)
    LinearLayout ptrframelayout;
    Unbinder unbinder;

    private ArrayList<Fragment> list;
    private Home_ViewPagerAdapter myPageAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.synthetical_item;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {
        list = new ArrayList<>();

        Blogs_Fragment blogs_fragment = new Blogs_Fragment();
        Exercise_Fragment exercise_fragment = new Exercise_Fragment();
        Inquire_Fragment inquire_fragment = new Inquire_Fragment();
        Message_Fragment message_fragment = new Message_Fragment();

        list.add(message_fragment);
        list.add(blogs_fragment);
        list.add(inquire_fragment);
        list.add(exercise_fragment);



        myPageAdapter = new Home_ViewPagerAdapter(getChildFragmentManager(), list);
        //加载多少Fragment
        syntheticalPager.setOffscreenPageLimit(4);

        syntheticalPager.setAdapter(myPageAdapter);


        syntheticalTab.setupWithViewPager(syntheticalPager);
        syntheticalTab.setTabMode(TabLayout.MODE_FIXED);
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
}
