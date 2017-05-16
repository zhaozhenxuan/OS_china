package com.example.administrator.os_china.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.base.BaseActivity;
import com.example.administrator.os_china.model.entity.Answers_beans;
import com.example.administrator.os_china.model.entity.Newslist_Xiangqing_Beans;
import com.example.administrator.os_china.model.http.biz.message.INewsModel;
import com.example.administrator.os_china.model.http.biz.message.NewsMineImpl;
import com.example.administrator.os_china.model.http.callback.MyCallBack;
import com.example.administrator.os_china.utils.Urls;
import com.thoughtworks.xstream.XStream;

import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.id.list;

/**
 * Created by Administrator on 2017/5/16 0016.
 */

public class NewsList_Activity extends BaseActivity {
    @BindView(R.id.newslist_web_view)
    WebView newslistWebView;
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
        Intent intent = getIntent();
        Id = intent.getStringExtra("id");
        Log.i("tag", "接受ID" + Id);
    }

    @Override
    protected void loderData() {


        iNewsModel.Newslist_Xiangqing(Id, new MyCallBack() {
            @Override
            public void onSuccess(String result) {

                Log.e("bbb", "获取到的数据：" + result);
                XStream xStream = new XStream();
                xStream.alias("oschina", Newslist_Xiangqing_Beans.class);
                xStream.alias("relative", Newslist_Xiangqing_Beans.NewsBean.RelativeBean.class);

                Newslist_Xiangqing_Beans beans = (Newslist_Xiangqing_Beans) xStream.fromXML(result);

                rurl = beans.getNews().getUrl();


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

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
