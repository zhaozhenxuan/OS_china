package com.example.administrator.os_china.fragment.trends_fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.activity.DT_Activity;
import com.example.administrator.os_china.adapter.trends_Adapter.Trends_Adapter;
import com.example.administrator.os_china.base.BaseFragment;
import com.example.administrator.os_china.model.entity.trends_beans.Trends_Beans;
import com.example.administrator.os_china.model.http.biz.message.dt.DtImpl;
import com.example.administrator.os_china.model.http.biz.message.dt.IDt;
import com.example.administrator.os_china.model.http.callback.MyCallBack;
import com.example.administrator.os_china.utils.ThreadUtils;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import in.srain.cube.views.ptr.PtrClassicDefaultFooter;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

import static android.media.CamcorderProfile.get;

/**
 * Created by Administrator on 2017/5/18 0018.
 * 每日乱弹
 */


public class MeiriDT_Fragment extends BaseFragment{

    private ListView trends_newsdt__listview;
    private Trends_Adapter pagerAdapter;
    private List<Trends_Beans.TweetBean> list = new ArrayList<>();
    private PtrFrameLayout frameLayout;
    private IDt iDt;
    private int page = 1;
    private String st;

    @Override
    public int getLayoutId() {
        return R.layout.trends_meiridt_item;
    }

    @Override
    public void initView(View view) {
        trends_newsdt__listview = (ListView) getActivity().findViewById(R.id.trends_meiri__listview);
        frameLayout = (PtrFrameLayout) getActivity().findViewById(R.id.meiri_ptr);
    }

    @Override
    public void initData() {
        iDt = new DtImpl();


        /**
         * 上拉加载和下拉刷新的
         */
        PtrClassicDefaultHeader defaultHeader = new PtrClassicDefaultHeader(getActivity());
        PtrClassicDefaultFooter defaultFooter = new PtrClassicDefaultFooter(getActivity());

        frameLayout.setHeaderView(defaultHeader);
        frameLayout.setFooterView(defaultFooter);
        frameLayout.addPtrUIHandler(defaultFooter);
        frameLayout.addPtrUIHandler(defaultHeader);

        frameLayout.setPtrHandler(new PtrDefaultHandler2() {
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
                                pagerAdapter.notifyDataSetChanged();
                                frameLayout.refreshComplete();
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
                                pagerAdapter.notifyDataSetChanged();
                                frameLayout.refreshComplete();
                            }
                        });
                    }
                }, 3000);
            }
        });
    }

    @Override
    public void initListener() {
        trends_newsdt__listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = list.get(position).getPortrait();
                String content = list.get(position).getBody();
                String name = list.get(position).getAuthor();
                Intent intent = new Intent(getActivity() , DT_Activity.class);
                intent.putExtra("url",url);
                intent.putExtra("content",content);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }

    @Override
    public void loderData() {
        st = page + "";
        iDt.MineDT(st, new MyCallBack() {
            @Override
            public void onSuccess(String result) {

                XStream xStream = new XStream();
                xStream.alias("oschina", Trends_Beans.class);
                xStream.alias("tweet", Trends_Beans.TweetBean.class);
                xStream.alias("user", Trends_Beans.TweetBean.UserBean.class);

                Trends_Beans beans = (Trends_Beans) xStream.fromXML(result);

                List<Trends_Beans.TweetBean> tweets = beans.getTweets();

                list.addAll(tweets);

                pagerAdapter = new Trends_Adapter(getActivity(), list);

                trends_newsdt__listview.setAdapter(pagerAdapter);


            }

            @Override
            public void onError(String MsgError) {

            }
        });
    }
}
