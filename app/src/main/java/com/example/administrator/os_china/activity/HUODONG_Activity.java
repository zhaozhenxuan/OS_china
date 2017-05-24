package com.example.administrator.os_china.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.adapter.find_Adapter.HuoDong_Adapter;
import com.example.administrator.os_china.adapter.synthetical_Adapter.MyMessageAdapter;
import com.example.administrator.os_china.base.BaseActivity;
import com.example.administrator.os_china.fragment.Synthetical_fragment.Message_Fragment;
import com.example.administrator.os_china.model.entity.find_Beans.HuoDong_Bean;
import com.example.administrator.os_china.model.entity.synthetical_beans.Beans;
import com.example.administrator.os_china.model.http.biz.message.news.INewsModel;
import com.example.administrator.os_china.model.http.biz.message.news.NewsMineImpl;
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
import in.srain.cube.views.ptr.PtrClassicDefaultFooter;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

import static android.R.id.list;
import static com.example.administrator.os_china.R.id.framelayut;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class HUODONG_Activity extends BaseActivity {


    INewsModel iNewsModel;

    @BindView(R.id.img_btn_huodong)
    ImageView imgBtnHuodong;

    @BindView(R.id.xiangqing_tv)
    TextView xiangqingTv;

    @BindView(R.id.huodong_listview)
    ListView huodongListview;

    @BindView(R.id.huodong_framelayut)
    PtrFrameLayout huodongFramelayut;

    private int page = 1;
    private String str;
    private List<HuoDong_Bean.EventBean> list = new ArrayList<>();
    private HuoDong_Adapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.huodong_activity_item;
    }

    @Override
    protected void initData() {
        iNewsModel = new NewsMineImpl();

        /**
         * 上拉加载和下拉刷新的
         */
        PtrClassicDefaultHeader defaultHeader = new PtrClassicDefaultHeader(HUODONG_Activity.this);
        PtrClassicDefaultFooter defaultFooter = new PtrClassicDefaultFooter(HUODONG_Activity.this);

        huodongFramelayut.setHeaderView(defaultHeader);
        huodongFramelayut.setFooterView(defaultFooter);
        huodongFramelayut.addPtrUIHandler(defaultFooter);
        huodongFramelayut.addPtrUIHandler(defaultHeader);

        huodongFramelayut.setPtrHandler(new PtrDefaultHandler2() {
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
                                adapter.notifyDataSetChanged();
                                huodongFramelayut.refreshComplete();
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
                                adapter.notifyDataSetChanged();
                                huodongFramelayut.refreshComplete();
                            }
                        });
                    }
                }, 3000);
            }
        });


    }

    @Override
    protected void initView() {
        View view = LayoutInflater.from(this).inflate(R.layout.baner_item , null);
        RollPagerView rollPagerView = (RollPagerView) view.findViewById(R.id.synthetical_pager);

        //添加listview头视图
        huodongListview.addHeaderView(view);

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
        rollPagerView.setHintView(new ColorPointHintView(this , Color.YELLOW, Color.WHITE));
        //mRollViewPager.setHintView(new TextHintView(this));
        //mRollViewPager.setHintView(null);
    }


    @Override
    protected void loderData() {

        str = page + "";
        iNewsModel.HuoDong(str, new MyCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.i("HUODONG", "请求回来的数据:" + result);

                XStream xStream = new XStream();
                xStream.alias("oschina", HuoDong_Bean.class);
                xStream.alias("event", HuoDong_Bean.EventBean.class);

                HuoDong_Bean bean = (HuoDong_Bean) xStream.fromXML(result);
                List<HuoDong_Bean.EventBean> events = bean.getEvents();
                list.addAll(events);

                adapter = new HuoDong_Adapter(HUODONG_Activity.this , list);

                huodongListview.setAdapter(adapter);



            }

            @Override
            public void onError(String MsgError) {

            }
        });

    }

    @Override
    protected void initListener() {

        huodongListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String id1 = list.get(position - 1 ).getId();
                Intent intent = new Intent(HUODONG_Activity.this , HD_XiangQing_Activity.class);
                intent.putExtra("id" , id1);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
