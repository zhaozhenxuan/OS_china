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
import com.example.administrator.os_china.model.entity.synthetical_beans.Wenda_Xiangqing_Beans;
import com.example.administrator.os_china.model.http.biz.message.news.INewsModel;
import com.example.administrator.os_china.model.http.biz.message.news.NewsMineImpl;
import com.example.administrator.os_china.model.http.callback.MyCallBack;
import com.thoughtworks.xstream.XStream;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public class Wenda_Xiangqing_Activity extends BaseActivity {
    @BindView(R.id.wenda_img_btn_xiangqing)
    ImageView wendaImgBtnXiangqing;
    @BindView(R.id.wenda_xiangqing_tv)
    TextView wendaXiangqingTv;
    @BindView(R.id.wenda_number)
    TextView wendaNumber;
    private String Id;
    private INewsModel iNewsModel;
    private String rurl;

    @BindView(R.id.wenda_web_view)
    WebView wendaWebView;

    @Override
    protected int getLayoutId() {
        return R.layout.wenda_xiangqing_item;
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
        wendaXiangqingTv.setText(intent.getStringExtra("text"));
        Log.i("tag", "接受ID:" + Id);
        iNewsModel.Wenda__Xiangqing(Id, new MyCallBack() {
            @Override
            public void onSuccess(String result) {

                Log.e("bbb", "获取到的数据：" + result);
                XStream xStream = new XStream();
                xStream.alias("oschina", Wenda_Xiangqing_Beans.class);

                Wenda_Xiangqing_Beans beans = (Wenda_Xiangqing_Beans) xStream.fromXML(result);

                wendaNumber.setText(beans.getPost().getAnswerCount());
                rurl = beans.getPost().getUrl();

                wendaWebView.loadUrl(rurl);

                wendaWebView.setWebViewClient(new WebViewClient() {
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
        wendaImgBtnXiangqing.setOnClickListener(new View.OnClickListener() {
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
