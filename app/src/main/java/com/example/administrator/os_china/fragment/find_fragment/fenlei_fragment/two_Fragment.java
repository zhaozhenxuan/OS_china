package com.example.administrator.os_china.fragment.find_fragment.fenlei_fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.os_china.App;
import com.example.administrator.os_china.R;
import com.example.administrator.os_china.adapter.find_Adapter.Fenlei_Adapter;
import com.example.administrator.os_china.base.BaseFragment;
import com.example.administrator.os_china.model.entity.find_Beans.Fenlei_Beans;
import com.example.administrator.os_china.model.http.biz.message.ruanjian.IRuanJian;
import com.example.administrator.os_china.model.http.biz.message.ruanjian.RuanJianImpl;
import com.example.administrator.os_china.model.http.callback.MyCallBack;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class Two_Fragment extends BaseFragment {

    @BindView(R.id.two_listview)
    ListView twoListview;
    Unbinder unbinder;

    private String twotag;
    private String threetag;
    private IRuanJian iRuanJian;

    private Fenlei_Adapter adapter;
    private List<Fenlei_Beans.SoftwareTypeBean> list = new ArrayList<>();
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;


    @Override
    public int getLayoutId() {
        return R.layout.fenlei_two_fragment_item;
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

        twoListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                threetag = list.get(position).getTag();

                SharedPreferences preferences=App.activity.getSharedPreferences("three",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("threetagagain",threetag);
                editor.commit();


                fragmentManager = App.activity.getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fenlei_fl , new Three_fragment() , Three_fragment.class.getSimpleName());
                transaction.commit();
            }
        });

    }

    @Override
    public void loderData() {

        /**
         * SP传值==》接
         */
        SharedPreferences preferences= App.activity.getSharedPreferences("two", Context.MODE_PRIVATE);
        twotag = preferences.getString("twotagagain",null);

        iRuanJian.fenlei_two(twotag, new MyCallBack() {
            @Override
            public void onSuccess(String result) {

                XStream xStream = new XStream();
                xStream.alias("oschina",Fenlei_Beans.class);
                xStream.alias("softwareType",Fenlei_Beans.SoftwareTypeBean.class);

                Fenlei_Beans beans = (Fenlei_Beans) xStream.fromXML(result);

                List<Fenlei_Beans.SoftwareTypeBean> softwareTypes = beans.getSoftwareTypes();
                list.addAll(softwareTypes);

                adapter = new Fenlei_Adapter(getActivity() , list);

                twoListview.setAdapter(adapter);
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
