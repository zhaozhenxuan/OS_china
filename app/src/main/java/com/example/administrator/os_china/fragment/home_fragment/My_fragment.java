package com.example.administrator.os_china.fragment.home_fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.os_china.App;
import com.example.administrator.os_china.R;
import com.example.administrator.os_china.activity.AllMain_Activity;
import com.example.administrator.os_china.activity.Login_Activity;
import com.example.administrator.os_china.activity.Setting_Activity;
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
    @BindView(R.id.username)
    TextView username;

    private SharedPreferences preferences;
    private String cookie;

    @Override
    public int getLayoutId() {
        return R.layout.my_item;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

        SharedPreferences preferences=App.activity.getSharedPreferences("user", Context.MODE_PRIVATE);
        String name = preferences.getString("name",null);
        Log.e("name","name :" +name);
        if(name == null){
            username.setText("点击头像登录");
        }else{
            username.setText(name);
        }

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

                Intent intent1 = new Intent(getActivity(), Setting_Activity.class);
                startActivity(intent1);

                break;
            case R.id.btn_qrcode:

                break;
            case R.id.image_login:

                preferences = App.activity.getSharedPreferences("data", Context.MODE_PRIVATE);
                cookie = preferences.getString("cookie", null);
                if (cookie == null) {
                    Intent intent = new Intent(getActivity(), Login_Activity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "您已经登录成功", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.WDXX:
                preferences = App.activity.getSharedPreferences("data", Context.MODE_PRIVATE);
                cookie = preferences.getString("cookie", null);
                if (cookie == null) {
                    Intent intent = new Intent(getActivity(), Login_Activity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity() , AllMain_Activity.class);
                    startActivity(intent);
                }


                break;
            case R.id.WDBK:
                preferences = App.activity.getSharedPreferences("data", Context.MODE_PRIVATE);
                cookie = preferences.getString("cookie", null);
                if (cookie == null) {
                    Intent intent = new Intent(getActivity(), Login_Activity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity() , AllMain_Activity.class);
                    startActivity(intent);
                }
                break;
            case R.id.WDWD:
                preferences = App.activity.getSharedPreferences("data", Context.MODE_PRIVATE);
                cookie = preferences.getString("cookie", null);
                if (cookie == null) {
                    Intent intent = new Intent(getActivity(), Login_Activity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity() , AllMain_Activity.class);
                    startActivity(intent);
                }
                break;
            case R.id.WDHD:
                preferences = App.activity.getSharedPreferences("data", Context.MODE_PRIVATE);
                cookie = preferences.getString("cookie", null);
                if (cookie == null) {
                    Intent intent = new Intent(getActivity(), Login_Activity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity() , AllMain_Activity.class);
                    startActivity(intent);
                }
                break;
            case R.id.WDTD:
                preferences = App.activity.getSharedPreferences("data", Context.MODE_PRIVATE);
                cookie = preferences.getString("cookie", null);
                if (cookie == null) {
                    Intent intent = new Intent(getActivity(), Login_Activity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity() , AllMain_Activity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
