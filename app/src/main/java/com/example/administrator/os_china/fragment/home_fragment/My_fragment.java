package com.example.administrator.os_china.fragment.home_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.activity.Login_Activity;
import com.example.administrator.os_china.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/14 0014.
 */

public class My_fragment extends BaseFragment {
    @BindView(R.id.my_setting)
    ImageView mySetting;
    @BindView(R.id.btn_qrcode)
    ImageView btnQrcode;
    @BindView(R.id.image_login)
    ImageView imageLogin;
    @BindView(R.id.WDXX_image)
    ImageView WDXXImage;
    @BindView(R.id.WDXX)
    RelativeLayout WDXX;
    @BindView(R.id.WDBK_image)
    ImageView WDBKImage;
    @BindView(R.id.WDBK)
    RelativeLayout WDBK;
    @BindView(R.id.WDWD_image)
    ImageView WDWDImage;
    @BindView(R.id.WDWD)
    RelativeLayout WDWD;
    @BindView(R.id.WDHD_image)
    ImageView WDHDImage;
    @BindView(R.id.WDHD)
    RelativeLayout WDHD;
    @BindView(R.id.WDTD_image)
    ImageView WDTDImage;
    @BindView(R.id.WDTD)
    RelativeLayout WDTD;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.my_item;
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

    @OnClick({R.id.my_setting, R.id.btn_qrcode, R.id.image_login, R.id.WDXX, R.id.WDBK, R.id.WDWD, R.id.WDHD, R.id.WDTD})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_setting:

                break;
            case R.id.btn_qrcode:

                break;
            case R.id.image_login:

                Intent intent = new Intent(getActivity() , Login_Activity.class);
                startActivity(intent);

                break;
            case R.id.WDXX:

                break;
            case R.id.WDBK:

                break;
            case R.id.WDWD:

                break;
            case R.id.WDHD:

                break;
            case R.id.WDTD:

                break;
        }
    }
}
