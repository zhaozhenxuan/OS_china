package com.example.administrator.os_china.fragment.find_fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.base.BaseFragment;
import com.example.administrator.os_china.model.http.biz.message.ruanjian.IRuanJian;
import com.example.administrator.os_china.model.http.biz.message.ruanjian.RuanJianImpl;
import com.example.administrator.os_china.model.http.callback.MyCallBack;
import com.thoughtworks.xstream.XStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class Tuijian_Fragment extends BaseFragment {

    @BindView(R.id.tuijian_listview)
    ListView tuijianListview;
    Unbinder unbinder;

    private int page = 1;
    private String st;
    private IRuanJian iRuanJian;
    @Override
    public int getLayoutId() {
        return R.layout.tuijian_fragment_item;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {
        iRuanJian = new RuanJianImpl();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void loderData() {
        st = page + "";
        iRuanJian.RJ_tuijian(st, new MyCallBack() {
            @Override
            public void onSuccess(String result) {


            }

            @Override
            public void onError(String MsgError) {

            }
        });

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
