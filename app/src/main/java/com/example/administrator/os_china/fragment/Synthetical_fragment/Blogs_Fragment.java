package com.example.administrator.os_china.fragment.Synthetical_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.adapter.BlogsAdapter;
import com.example.administrator.os_china.base.BaseFragment;
import com.example.administrator.os_china.model.entity.Blogs_beans;
import com.example.administrator.os_china.model.http.biz.message.INewsModel;
import com.example.administrator.os_china.model.http.biz.message.NewsMineImpl;
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

import static com.example.administrator.os_china.R.id.framelayut;

/**
 * Created by Administrator on 2017/5/14 0014.
 */

public class Blogs_Fragment extends BaseFragment {

    @BindView(R.id.blogs_listview)
    ListView blogsListview;
    Unbinder unbinder;
    @BindView(R.id.blogs_ptr)
    PtrFrameLayout blogsPtr;
    private INewsModel iNewsModel;
    private List<Blogs_beans.BlogBean> list = new ArrayList<>();
    private BlogsAdapter blogsAdapter;
    private int page = 1;
    private String st;

    @Override
    public int getLayoutId() {
        return R.layout.synthetical_blogs_item;
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

        blogsPtr.setHeaderView(defaultHeader);
        blogsPtr.setFooterView(defaultFooter);
        blogsPtr.addPtrUIHandler(defaultFooter);
        blogsPtr.addPtrUIHandler(defaultHeader);

        blogsPtr.setPtrHandler(new PtrDefaultHandler2() {
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
                                blogsAdapter.notifyDataSetChanged();
                                blogsPtr.refreshComplete();
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
                                blogsAdapter.notifyDataSetChanged();
                                blogsPtr.refreshComplete();
                            }
                        });
                    }
                }, 3000);
            }
        });


    }

    @Override
    public void initListener() {

    }

    @Override
    public void loderData() {
        st = page + "";
        iNewsModel.BlogsList(st, new MyCallBack() {
            @Override
            public void onSuccess(String result) {
                XStream xStream = new XStream();
                xStream.alias("oschina", Blogs_beans.class);
                xStream.alias("blog", Blogs_beans.BlogBean.class);

                Blogs_beans beans = (Blogs_beans) xStream.fromXML(result);

                List<Blogs_beans.BlogBean> blogs = beans.getBlogs();
                list.addAll(blogs);

                blogsAdapter = new BlogsAdapter(getActivity(), list);

                blogsListview.setAdapter(blogsAdapter);

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
