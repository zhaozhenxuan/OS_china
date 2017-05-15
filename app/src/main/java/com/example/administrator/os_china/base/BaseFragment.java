package com.example.administrator.os_china.base;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public abstract class BaseFragment extends Fragment {

    private boolean isFirst = true;
    private Bundle params;
    Unbinder unbinder;

    /**
     *加载控件
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)  {

            return inflater.inflate(getLayoutId() , container , false);


    }

    /**
     *控件加载完成的时候
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this , view);
        isFirst = true;
        initView(view);
        initListener();
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(isFirst = true){
            loderData();
            isFirst = false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            onHidden();
        }else{
            onShow();
        }
    }

    /**
     *获取布局文件
     */
    public abstract int getLayoutId();

    /**
     *初始化布局的方法
     */
    public abstract void initView(View view);

    /**
     *初始化数据
     */
    public abstract void initData();

    /**
     *初始化监听方法
     */
    public abstract void initListener();

    /**
     *加载数据
     */
    public abstract void loderData();

    /**
     *当此Fragment显示的时候调用的方法
     */
    public  void onShow(){};

    /**
     *当此Fragment不现实的时候调用的方法
     */
    public  void onHidden(){};

    /**
     *接受传递回来的数据
     */
    public  void setParams(Bundle bundle){
        this.params = bundle;
    }

    public Bundle getParams(){
        return params;
    }

}
