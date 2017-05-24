package com.example.administrator.os_china.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.adapter.find_Adapter.FenLeiPager_Adapter;
import com.example.administrator.os_china.base.BaseActivity;
import com.example.administrator.os_china.fragment.find_fragment.Fenlei_Fragment;
import com.example.administrator.os_china.fragment.find_fragment.Guochan_Fragment;
import com.example.administrator.os_china.fragment.find_fragment.Remen_Fragment;
import com.example.administrator.os_china.fragment.find_fragment.Tuijian_Fragment;
import com.example.administrator.os_china.fragment.find_fragment.Zuixin_Fragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/23 0023.
 */
public class KYRJ_Activity extends BaseActivity {

    @BindView(R.id.img_btn_kyrj)
    ImageView imgBtn;

    @BindView(R.id.xiangqing_tv)
    TextView xiangqingTv;

    @BindView(R.id.kyrj_tab)
    TabLayout kyrjTab;

    @BindView(R.id.kyrj_pager)
    ViewPager kyrjPager;

    private ArrayList<Fragment> list;
    private FenLeiPager_Adapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.kyrj_activity_item;
    }

    @Override
    protected void initData() {

        list = new ArrayList<>();

        list.add(new Fenlei_Fragment());
        list.add(new Tuijian_Fragment());
        list.add(new Zuixin_Fragment());
        list.add(new Remen_Fragment());
        list.add(new Guochan_Fragment());

        adapter = new FenLeiPager_Adapter(getSupportFragmentManager() , list);

        kyrjPager.setOffscreenPageLimit(5);

        kyrjPager.setAdapter(adapter);

        kyrjTab.setupWithViewPager(kyrjPager);
        kyrjTab.setTabMode(TabLayout.MODE_FIXED);


    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loderData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
