package com.example.administrator.os_china.fragment.home_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/14 0014.
 */

public class Find_fragment extends BaseFragment {

    @BindView(R.id.MYTJ)
    RelativeLayout MYTJ;
    @BindView(R.id.KYRJ)
    RelativeLayout KYRJ;
    @BindView(R.id.SYS)
    RelativeLayout SYS;
    @BindView(R.id.YYY)
    RelativeLayout YYY;
    @BindView(R.id.FUJIN)
    RelativeLayout FUJIN;
    @BindView(R.id.HUODONG)
    RelativeLayout HUODONG;
    Unbinder unbinder;
    @BindView(R.id.search_btn3)
    ImageView searchBtn3;

    @Override
    public int getLayoutId() {
        return R.layout.find_item;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

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

    @OnClick({R.id.MYTJ, R.id.KYRJ, R.id.SYS, R.id.YYY, R.id.FUJIN, R.id.HUODONG})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.MYTJ:

                break;
            case R.id.KYRJ:

                break;
            case R.id.SYS:

                break;
            case R.id.YYY:

                break;
            case R.id.FUJIN:

                break;
            case R.id.HUODONG:

                break;
        }
    }

    @OnClick(R.id.search_btn3)
    public void onViewClicked() {
    }
}
