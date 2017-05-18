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
import com.example.administrator.os_china.activity.Wenda_Xiangqing_Activity;
import com.example.administrator.os_china.adapter.synthetical_Adapter.AnswersAdapter;
import com.example.administrator.os_china.base.BaseFragment;
import com.example.administrator.os_china.model.entity.synthetical_beans.Answers_beans;
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
 * 技术问答Fragment
 */

public class Inquire_Fragment extends BaseFragment {

    @BindView(R.id.inquire_listview)
    ListView inquireListview;
    @BindView(R.id.inquire_ptr)
    PtrFrameLayout inquirePtr;
    Unbinder unbinder;
    private INewsModel iNewsModel;
    private int page = 1;
    private String st;
    private List<Answers_beans.PostBean> list = new ArrayList<>();
    private AnswersAdapter answersAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.synthetical_inquire_item;
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

        inquirePtr.setHeaderView(defaultHeader);
        inquirePtr.setFooterView(defaultFooter);
        inquirePtr.addPtrUIHandler(defaultFooter);
        inquirePtr.addPtrUIHandler(defaultHeader);

        inquirePtr.setPtrHandler(new PtrDefaultHandler2() {
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
                                answersAdapter.notifyDataSetChanged();
                                inquirePtr.refreshComplete();
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
                                answersAdapter.notifyDataSetChanged();
                                inquirePtr.refreshComplete();
                            }
                        });
                    }
                }, 3000);
            }
        });

    }

    @Override
    public void initListener() {
        inquireListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = list.get(position ).getId();
                Log.e("TTT","传过去的数据:"+str);
                Intent intent = new Intent(getActivity() , Wenda_Xiangqing_Activity.class);
                intent.putExtra("id" , str);
                intent.putExtra("text" , "问答详情");
                startActivity(intent);
            }
        });
    }

    @Override
    public void loderData() {
        st = page + "";
        iNewsModel.AnswersList(st, new MyCallBack() {
            @Override
            public void onSuccess(String result) {

                XStream xStream = new XStream();
                xStream.alias("oschina",Answers_beans.class);
                xStream.alias("post", Answers_beans.PostBean.class);

                Answers_beans beans = (Answers_beans) xStream.fromXML(result);
                List<Answers_beans.PostBean> posts = beans.getPosts();

                list.addAll(posts);

                answersAdapter = new AnswersAdapter(getActivity(), list);

                inquireListview.setAdapter(answersAdapter);


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
