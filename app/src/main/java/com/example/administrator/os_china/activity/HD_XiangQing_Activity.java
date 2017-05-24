package com.example.administrator.os_china.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.base.BaseActivity;
import com.example.administrator.os_china.model.entity.find_Beans.HD_XiangQing_Bean;
import com.example.administrator.os_china.model.http.biz.message.news.INewsModel;
import com.example.administrator.os_china.model.http.biz.message.news.NewsMineImpl;
import com.example.administrator.os_china.model.http.callback.MyCallBack;
import com.thoughtworks.xstream.XStream;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class HD_XiangQing_Activity extends BaseActivity {

    @BindView(R.id.img_btn_hd)
    ImageView imgBtnHd;
    @BindView(R.id.HD_webview)
    WebView HDWebview;
    private String id;
    private INewsModel iNewsModel;

    @Override
    protected int getLayoutId() {
        return R.layout.hd_xiangqing_activity_item;
    }

    @Override
    protected void initData() {
        iNewsModel = new NewsMineImpl();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loderData() {

        iNewsModel.HD_XiangQing(id, new MyCallBack() {
            @Override
            public void onSuccess(String result) {

                XStream xStream = new XStream();
                xStream.alias("oschina", HD_XiangQing_Bean.class);

                HD_XiangQing_Bean bean = (HD_XiangQing_Bean) xStream.fromXML(result);
                String url = bean.getPost().getUrl();
                Log.w("huodongxiangqing", "打印Url ： " + url);

                HDWebview.loadUrl(url);
                //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
                HDWebview.setWebViewClient(new WebViewClient() {
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

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
