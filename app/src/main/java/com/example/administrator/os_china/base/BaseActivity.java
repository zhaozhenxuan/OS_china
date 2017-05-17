package com.example.administrator.os_china.base;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.os_china.App;
import com.example.administrator.os_china.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        fragmentManager = getSupportFragmentManager();
        App.activity = this;
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
        loderData();
    }

    //加载布局文件
    protected abstract int getLayoutId();

    //初始化数据
    protected abstract void initData();

    //初始化控件
    protected abstract void initView();

    //加载数据
    protected abstract void loderData();

    //初始化监听方法
    protected abstract void initListener();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
