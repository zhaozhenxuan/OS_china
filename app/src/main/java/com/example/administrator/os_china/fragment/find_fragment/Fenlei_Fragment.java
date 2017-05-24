package com.example.administrator.os_china.fragment.find_fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.administrator.os_china.App;
import com.example.administrator.os_china.R;
import com.example.administrator.os_china.base.BaseFragment;
import com.example.administrator.os_china.fragment.find_fragment.fenlei_fragment.One_Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class Fenlei_Fragment extends BaseFragment {

    @BindView(R.id.fenlei_fl)
    FrameLayout fenleiFl;
    Unbinder unbinder;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;


    @Override
    public int getLayoutId() {
        return R.layout.fenlei_fragment_item;
    }

    @Override
    public void initView(View view) {
        fragmentManager = App.activity.getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fenlei_fl , new One_Fragment() , One_Fragment.class.getSimpleName());
        transaction.commit();

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
}
