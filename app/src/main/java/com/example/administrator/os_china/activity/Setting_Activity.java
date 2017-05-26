package com.example.administrator.os_china.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public class Setting_Activity extends BaseActivity {
    @BindView(R.id.img_btn_setting)
    ImageView imgBtnSetting;
    @BindView(R.id.setting)
    RelativeLayout setting;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private SharedPreferences preferences1;
    private SharedPreferences.Editor editor1;

    @Override
    protected int getLayoutId() {
        return R.layout.setting_activity_item;
    }

    @Override
    protected void initData() {

        preferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = preferences.edit();

        preferences1 = getSharedPreferences("user", Context.MODE_PRIVATE);
        editor1 = preferences1.edit();
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

    @OnClick({R.id.img_btn_setting, R.id.setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_btn_setting:

                onBackPressed();
                break;
            case R.id.setting:
                editor.remove("cookie");
                editor.commit();

                editor1.remove("uid");
                editor1.remove("name");
                editor1.remove("img");
                editor1.commit();
                Toast.makeText(this, "注销成功", Toast.LENGTH_SHORT).show();

                finish();


                break;
        }
    }
}
