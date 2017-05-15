package com.example.administrator.os_china.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.base.BaseActivity;
import com.example.administrator.os_china.fragment.home_fragment.Find_fragment;
import com.example.administrator.os_china.fragment.home_fragment.My_fragment;
import com.example.administrator.os_china.fragment.home_fragment.Synthetical_fragment;
import com.example.administrator.os_china.fragment.home_fragment.Trends_fragment;
import com.example.administrator.os_china.utils.FragmentBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.contentGroup)
    FrameLayout contentGroup;
    @BindView(R.id.ZongHeBtn)
    RadioButton ZongHeBtn;
    @BindView(R.id.DongTanBtn)
    RadioButton DongTanBtn;
    @BindView(R.id.AddBtn)
    ImageView AddBtn;
    @BindView(R.id.FaXianBtn)
    RadioButton FaXianBtn;
    @BindView(R.id.MineBtn)
    RadioButton MineBtn;
    @BindView(R.id.Bottom_Group)
    RadioGroup BottomGroup;
    @BindView(R.id.activity_home)
    LinearLayout activityHome;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        FragmentBuilder.getInstance().start(Synthetical_fragment.class);
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

    @OnClick({R.id.ZongHeBtn, R.id.DongTanBtn, R.id.AddBtn, R.id.FaXianBtn, R.id.MineBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ZongHeBtn:
                FragmentBuilder.getInstance().start(Synthetical_fragment.class);
                break;
            case R.id.DongTanBtn:
                FragmentBuilder.getInstance().start(Trends_fragment.class);
                break;
            case R.id.AddBtn:
                break;
            case R.id.FaXianBtn:
                FragmentBuilder.getInstance().start(Find_fragment.class);
                break;
            case R.id.MineBtn:
                FragmentBuilder.getInstance().start(My_fragment.class);
                break;
        }
    }
}
