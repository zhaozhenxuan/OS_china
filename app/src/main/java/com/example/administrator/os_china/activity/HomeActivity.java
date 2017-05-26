package com.example.administrator.os_china.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.os_china.App;
import com.example.administrator.os_china.R;
import com.example.administrator.os_china.base.BaseActivity;
import com.example.administrator.os_china.base.BaseFragment;
import com.example.administrator.os_china.fragment.home_fragment.Find_fragment;
import com.example.administrator.os_china.fragment.home_fragment.My_fragment;
import com.example.administrator.os_china.fragment.home_fragment.Synthetical_fragment;
import com.example.administrator.os_china.fragment.home_fragment.Trends_fragment;
import com.example.administrator.os_china.utils.FragmentBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    private long mExitTime;
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
        FragmentBuilder.getInstance().containerId(R.id.contentGroup).start(Synthetical_fragment.class).build();
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
                FragmentBuilder.getInstance().containerId(R.id.contentGroup).start(Synthetical_fragment.class).build();
                break;
            case R.id.DongTanBtn:
                FragmentBuilder.getInstance().containerId(R.id.contentGroup).start(Trends_fragment.class).build();
                break;
            case R.id.AddBtn:

                SharedPreferences preferences = getSharedPreferences("data", Context.MODE_PRIVATE);
                String cookie=preferences.getString("cookie",null);

                if(cookie == null){
                    Intent intent = new Intent(HomeActivity.this , Login_Activity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(HomeActivity.this , Add_Activity.class);
                    startActivity(intent);
                }

                break;
            case R.id.FaXianBtn:
                FragmentBuilder.getInstance().containerId(R.id.contentGroup).start(Find_fragment.class).build();
                break;
            case R.id.MineBtn:
                FragmentBuilder.getInstance().containerId(R.id.contentGroup).start(My_fragment.class).build();
                break;
        }
    }

    /**
     * 捕获back键 当back键被按下时
     */
    @Override
    public void onBackPressed() {
        FragmentManager manager = App.activity.getSupportFragmentManager();
        FragmentManager.BackStackEntry entry = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1);
        String name = entry.getName();
        if ("Synthetical_fragment".equals(name) || "Trends_fragment".equals(name)
                || "Find_fragment".equals(name) || "My_fragment".equals(name)) {
            System.exit(0);
        } else {
            manager.popBackStackImmediate();
            String fragmentName = App.activity.getSupportFragmentManager().getBackStackEntryAt(App.activity.getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
            BaseFragment fragment = (BaseFragment) App.activity.getSupportFragmentManager().findFragmentByTag(fragmentName);
            FragmentBuilder.getInstance().setLastFragment(fragment);
        }

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if ((System.currentTimeMillis() - mExitTime) > 2000) {

                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();

            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
