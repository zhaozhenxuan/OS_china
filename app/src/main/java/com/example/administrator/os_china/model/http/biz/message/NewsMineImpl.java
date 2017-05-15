package com.example.administrator.os_china.model.http.biz.message;

import android.util.Log;

import com.example.administrator.os_china.model.http.callback.HttpFactory;
import com.example.administrator.os_china.model.http.callback.MyCallBack;
import com.example.administrator.os_china.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class NewsMineImpl implements INewsModel {
    /**
     *
     * @param pageIndex
     * @param myCallBack
     * 资讯请求的业务类
     */
    @Override
    public void NewsList(String pageIndex, MyCallBack myCallBack) {
        Map<String , String> params = new HashMap<>();
        params.put("catalog" , "1" );
        params.put("pageIndex" , pageIndex);
        params.put("pageSize" , "20");

        HttpFactory.onCreate().doGet(Urls.ZIXUN , params , myCallBack);
        Log.e("TAG","URL--" + Urls.ZIXUN);
    }

    /**
     *
     * @param pageIndex
     * @param myCallBack
     * 博客请求的业务类
     */
    @Override
    public void BlogsList(String pageIndex, MyCallBack myCallBack) {
        Map<String , String> params = new HashMap<>();
        params.put("type" , "latest" );
        params.put("pageIndex" , pageIndex);
        params.put("pageSize" , "20");

        HttpFactory.onCreate().doGet(Urls.BOKE , params , myCallBack);
        Log.e("TAG","URL--" + Urls.BOKE);
    }

    /**
     *
     * @param pageIndex
     * @param myCallBack
     * 技术问答，提问的业务类
     */
    @Override
    public void AnswersList(String pageIndex, MyCallBack myCallBack) {
        Map<String , String> params = new HashMap<>();
        params.put("catalog" , "1" );
        params.put("pageIndex" , pageIndex);
        params.put("pageSize" , "10");

        HttpFactory.onCreate().doGet(Urls.TIWEN , params , myCallBack);
        Log.e("TAG","URL--" + Urls.TIWEN);
    }

    /**
     *
     * @param pageIndex
     * @param myCallBack
     * 每日一博业务类
     */
    @Override
    public void ZuixinList(String pageIndex, MyCallBack myCallBack) {
        Map<String , String> params = new HashMap<>();
        params.put("type" , "latest" );
        params.put("pageIndex" , pageIndex);
        params.put("pageSize" , "10");

        HttpFactory.onCreate().doGet(Urls.ZUIXIN , params , myCallBack);
        Log.e("TAG","URL--" + Urls.ZUIXIN);
    }
}
