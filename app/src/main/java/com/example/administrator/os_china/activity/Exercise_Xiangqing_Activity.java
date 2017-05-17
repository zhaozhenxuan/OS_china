package com.example.administrator.os_china.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.base.BaseActivity;
import com.example.administrator.os_china.model.entity.Blogs_Xiangqing_Beans;
import com.example.administrator.os_china.model.http.biz.message.INewsModel;
import com.example.administrator.os_china.model.http.biz.message.NewsMineImpl;
import com.example.administrator.os_china.model.http.callback.MyCallBack;
import com.thoughtworks.xstream.XStream;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public class Exercise_Xiangqing_Activity extends BaseActivity {
    @BindView(R.id.newslist_web_view)
    WebView newslistWebView;
    @BindView(R.id.img_btn_xiangqing)
    ImageView imgBtnXiangqing;
    @BindView(R.id.xiangqing_tv)
    TextView xiangqingTv;
    private String Id;
    private INewsModel iNewsModel;
    private String rurl;
    private String URL;

    @Override
    protected int getLayoutId() {
        return R.layout.newslist_item;
    }

    @Override
    protected void initData() {
        iNewsModel = new NewsMineImpl();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loderData() {
        Intent intent = getIntent();
        Id = intent.getStringExtra("id");
        xiangqingTv.setText(intent.getStringExtra("text"));
        Log.i("tag", "接受ID" + Id);

        iNewsModel.Exercise_Xiangqing(Id, new MyCallBack() {
            @Override
            public void onSuccess(String result) {

                Log.e("bbb", "获取到的数据：" + result);
                XStream xStream = new XStream();
                xStream.alias("oschina", Blogs_Xiangqing_Beans.class);

                Blogs_Xiangqing_Beans beans = (Blogs_Xiangqing_Beans) xStream.fromXML(result);

                rurl = beans.getBlog().getUrl();


                newslistWebView.loadUrl(rurl);
                //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
                newslistWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        // TODO Auto-generated method stub
                        //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                        view.loadUrl(url);
                        return true;
                    }
                });

            }

            @Override
            public void onError(String MsgError) {

            }
        });
    }

    @Override
    protected void initListener() {
        imgBtnXiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
