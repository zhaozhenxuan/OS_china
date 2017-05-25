package com.example.administrator.os_china.fragment.find_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.activity.RJ_xiangqing_Activity;
import com.example.administrator.os_china.adapter.find_Adapter.Fenlei_three_Adapter;
import com.example.administrator.os_china.base.BaseFragment;
import com.example.administrator.os_china.model.entity.find_Beans.Fenlei_three_Beans;
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

public class Guochan_Fragment extends BaseFragment {


    @BindView(R.id.guochan_listview)
    ListView guochanListview;
    Unbinder unbinder;
    private int page = 1;
    private String st;

    private IRuanJian iRuanJian;

    private List<Fenlei_three_Beans.SoftwareBean> list = new ArrayList<>();
    private Fenlei_three_Adapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.guochan_fragment_item;
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
        guochanListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity() , RJ_xiangqing_Activity.class);

                String url = list.get(position).getUrl();
                intent.putExtra("url",url);
                startActivity(intent);

            }
        });
    }

    @Override
    public void loderData() {
        st = page + "";
        iRuanJian.RJ_guochan(st, new MyCallBack() {
            @Override
            public void onSuccess(String result) {

                XStream xStream = new XStream();
                xStream.alias("oschina", Fenlei_three_Beans.class);
                xStream.alias("software", Fenlei_three_Beans.SoftwareBean.class);

                Fenlei_three_Beans beans = (Fenlei_three_Beans) xStream.fromXML(result);

                List<Fenlei_three_Beans.SoftwareBean> softwares = beans.getSoftwares();
                list.addAll(softwares);

                adapter = new Fenlei_three_Adapter(getActivity(), list);

                guochanListview.setAdapter(adapter);
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
