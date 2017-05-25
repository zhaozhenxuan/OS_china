package com.example.administrator.os_china.activity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public class RJ_xiangqing_Activity extends BaseActivity {

    @BindView(R.id.img_btn_RJ_xiangqing)
    ImageView imgBtnRJXiangqing;

    @BindView(R.id.RJ_xiangqing_tv)
    TextView RJXiangqingTv;

    @BindView(R.id.RJ_number)
    TextView RJNumber;

    @BindView(R.id.RJ_webview)
    WebView RJWebview;

    @Override
    protected int getLayoutId() {
        return R.layout.rj_xiangqing_activity_item;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loderData() {

        String url = getIntent().getStringExtra("url");

        RJWebview.loadUrl(url);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        RJWebview.setWebViewClient(new WebViewClient() {
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
    protected void initListener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
