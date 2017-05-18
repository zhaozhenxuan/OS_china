package com.example.administrator.os_china.fragment.Synthetical_fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.activity.Exercise_Xiangqing_Activity;
import com.example.administrator.os_china.adapter.synthetical_Adapter.Bolgs_MeiriAdapter;
import com.example.administrator.os_china.base.BaseFragment;
import com.example.administrator.os_china.model.entity.synthetical_beans.Bolgs_Zuixin_Beans;
import com.example.administrator.os_china.model.http.biz.message.news.INewsModel;
import com.example.administrator.os_china.model.http.biz.message.news.NewsMineImpl;
import com.example.administrator.os_china.model.http.callback.MyCallBack;
import com.example.administrator.os_china.utils.ThreadUtils;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrClassicDefaultFooter;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Administrator on 2017/5/9 0009.
 * 每日一博Fragment
 */

public class Exercise_Fragment extends BaseFragment {

    @BindView(R.id.exercise_listview)
    ListView exerciseListview;
    @BindView(R.id.exercise_ptr)
    PtrFrameLayout exercisePtr;
    Unbinder unbinder;
    private List<Bolgs_Zuixin_Beans.BlogBean> list = new ArrayList<>();
    private Bolgs_MeiriAdapter bolgs_meiriAdapter;
    private int page = 1;
    private String st;
    private INewsModel iNewsModel;

    @Override
    public int getLayoutId() {
        return R.layout.synthetical_exercise_item;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {
        iNewsModel = new NewsMineImpl();

        /**
         * 上拉加载和下拉刷新的
         */
        PtrClassicDefaultHeader defaultHeader = new PtrClassicDefaultHeader(getActivity());
        PtrClassicDefaultFooter defaultFooter = new PtrClassicDefaultFooter(getActivity());

        exercisePtr.setHeaderView(defaultHeader);
        exercisePtr.setFooterView(defaultFooter);
        exercisePtr.addPtrUIHandler(defaultFooter);
        exercisePtr.addPtrUIHandler(defaultHeader);

        exercisePtr.setPtrHandler(new PtrDefaultHandler2() {
            //加载更多
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        page++;
                        ThreadUtils.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                loderData();
                                bolgs_meiriAdapter.notifyDataSetChanged();
                                exercisePtr.refreshComplete();
                            }
                        });
                    }
                }, 3000);
            }

            //刷新数据
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        ThreadUtils.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                bolgs_meiriAdapter.notifyDataSetChanged();
                                exercisePtr.refreshComplete();
                            }
                        });
                    }
                }, 3000);
            }
        });

    }

    @Override
    public void initListener() {
       exerciseListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String str = list.get(position ).getId();
               Log.e("TTT","传过去的数据:"+str);
               Intent intent = new Intent(getActivity() , Exercise_Xiangqing_Activity.class);
               intent.putExtra("id" , str);
               intent.putExtra("text" , "博客详情");
               startActivity(intent);
           }
       });
    }

    @Override
    public void loderData() {
        st = page + "";
        iNewsModel.ZuixinList(st, new MyCallBack() {
            @Override
            public void onSuccess(String result) {

                XStream xStream = new XStream();
                xStream.alias("oschina",Bolgs_Zuixin_Beans.class);
                xStream.alias("blog", Bolgs_Zuixin_Beans.BlogBean.class);

                Bolgs_Zuixin_Beans bean = (Bolgs_Zuixin_Beans) xStream.fromXML(result);
                List<Bolgs_Zuixin_Beans.BlogBean> blogs = bean.getBlogs();

                list.addAll(blogs);

                bolgs_meiriAdapter = new Bolgs_MeiriAdapter(getActivity(), list);

                exerciseListview.setAdapter(bolgs_meiriAdapter);
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
