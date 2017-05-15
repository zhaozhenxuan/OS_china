package com.example.administrator.os_china.fragment.Synthetical_fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.adapter.MyMessageAdapter;
import com.example.administrator.os_china.base.BaseFragment;
import com.example.administrator.os_china.model.entity.Beans;
import com.example.administrator.os_china.model.http.biz.message.INewsModel;
import com.example.administrator.os_china.model.http.biz.message.NewsMineImpl;
import com.example.administrator.os_china.model.http.callback.MyCallBack;
import com.example.administrator.os_china.utils.ThreadUtils;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
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

import static com.example.administrator.os_china.R.id.synthetical_listview;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class Message_Fragment extends BaseFragment {


    @BindView(synthetical_listview)
    ListView syntheticalListview;
    @BindView(R.id.framelayut)
    PtrFrameLayout framelayut;
    Unbinder unbinder;
    private MyMessageAdapter myMessageAdapter;
    private List<Beans.NewsBean> list = new ArrayList<>();
    private INewsModel iNewsModel;
    private int page = 1;
    private String st;

    @Override
    public int getLayoutId() {
        return R.layout.synthetical_message_item;
    }

    @Override
    public void initView(View view) {

        view = LayoutInflater.from(getActivity()).inflate(R.layout.baner_item, null);
        RollPagerView rollPagerView = (RollPagerView) view.findViewById(R.id.synthetical_pager);

        //添加listview头视图
        syntheticalListview.addHeaderView(view);

        //设置播放时间间隔
        rollPagerView.setPlayDelay(3000);
        //设置透明度
        rollPagerView.setAnimationDurtion(500);
        //设置适配器
        rollPagerView.setAdapter(new TestNormalAdapter());

        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
        rollPagerView.setHintView(new ColorPointHintView(getActivity(), Color.YELLOW, Color.WHITE));
        //mRollViewPager.setHintView(new TextHintView(this));
        //mRollViewPager.setHintView(null);
    }

    @Override
    public void initData() {
        iNewsModel = new NewsMineImpl();

/**
 * 上拉加载和下拉刷新的
 */
        PtrClassicDefaultHeader defaultHeader = new PtrClassicDefaultHeader(getActivity());
        PtrClassicDefaultFooter defaultFooter = new PtrClassicDefaultFooter(getActivity());

        framelayut.setHeaderView(defaultHeader);
        framelayut.setFooterView(defaultFooter);
        framelayut.addPtrUIHandler(defaultFooter);
        framelayut.addPtrUIHandler(defaultHeader);

        framelayut.setPtrHandler(new PtrDefaultHandler2() {
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
                                myMessageAdapter.notifyDataSetChanged();
                                framelayut.refreshComplete();
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
                                myMessageAdapter.notifyDataSetChanged();
                                framelayut.refreshComplete();
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
        iNewsModel.NewsList(st, new MyCallBack() {
            @Override
            public void onSuccess(String result) {

                XStream xStream = new XStream();
                xStream.alias("oschina", Beans.class);
                xStream.alias("news", Beans.NewsBean.class);

                Beans beans = (Beans) xStream.fromXML(result);
                List<Beans.NewsBean> newslist = beans.getNewslist();
                list.addAll(newslist);

                myMessageAdapter = new MyMessageAdapter(getActivity(), list);

                syntheticalListview.setAdapter(myMessageAdapter);
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

    //轮播图适配器
    private class TestNormalAdapter extends StaticPagerAdapter {
        private int[] imgs = {
                R.drawable.bg_topic_1,
                R.drawable.bg_topic_2,
                R.drawable.bg_topic_3,
                R.drawable.bg_topic_4,
                R.drawable.bg_topic_5,
        };


        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }


        @Override
        public int getCount() {
            return imgs.length;
        }
    }
}
