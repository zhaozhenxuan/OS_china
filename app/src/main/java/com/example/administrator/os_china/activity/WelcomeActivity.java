package com.example.administrator.os_china.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.base.BaseActivity;
import com.example.administrator.os_china.utils.ThreadUtils;

import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initData() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        } , 2000);
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
}
