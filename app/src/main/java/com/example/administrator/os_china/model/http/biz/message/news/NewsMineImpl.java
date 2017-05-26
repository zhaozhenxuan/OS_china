package com.example.administrator.os_china.model.http.biz.message.news;

import android.util.Log;

import com.example.administrator.os_china.model.http.callback.HttpFactory;
import com.example.administrator.os_china.model.http.callback.MyCallBack;
import com.example.administrator.os_china.model.http.okhttp.OkhttpUtils;
import com.example.administrator.os_china.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class NewsMineImpl implements INewsModel {
    /**
     * @param pageIndex
     * @param myCallBack 资讯请求的业务类
     */
    @Override
    public void NewsList(String pageIndex, MyCallBack myCallBack) {
        Map<String, String> params = new HashMap<>();
        params.put("catalog", "1");
        params.put("pageIndex", pageIndex);
        params.put("pageSize", "20");

        HttpFactory.onCreate().doGet(Urls.ZIXUN, params, myCallBack);
    }

    /**
     * @param pageIndex
     * @param myCallBack 博客请求的业务类
     */
    @Override
    public void Blogs_Tuijian_List(String pageIndex, MyCallBack myCallBack) {
        Map<String, String> params = new HashMap<>();
        params.put("type", "recommend");
        params.put("pageIndex", pageIndex);
        params.put("pageSize", "20");

        HttpFactory.onCreate().doGet(Urls.TUIJIAN, params, myCallBack);
    }

    /**
     * @param pageIndex
     * @param myCallBack 技术问答，提问的业务类
     */
    @Override
    public void AnswersList(String pageIndex, MyCallBack myCallBack) {
        Map<String, String> params = new HashMap<>();
        params.put("catalog", "1");
        params.put("pageIndex", pageIndex);
        params.put("pageSize", "10");

        HttpFactory.onCreate().doGet(Urls.TIWEN, params, myCallBack);
    }

    /**
     * @param pageIndex
     * @param myCallBack 每日一博业务类
     */
    @Override
    public void ZuixinList(String pageIndex, MyCallBack myCallBack) {
        Map<String, String> params = new HashMap<>();
        params.put("type", "latest");
        params.put("pageIndex", pageIndex);
        params.put("pageSize", "10");

        HttpFactory.onCreate().doGet(Urls.ZUIXIN, params, myCallBack);
    }

    @Override
    public void Newslist_Xiangqing(String Id, MyCallBack myCallBack) {

        Map<String, String> params = new HashMap<>();
        params.put("id", Id);

        HttpFactory.onCreate().doGet(Urls.XINWEN, params, myCallBack);
        Log.i("tag", Urls.XINWEN);
    }

    @Override
    public void Blogs_Xiangqing(String Id, MyCallBack myCallBack) {
        Map<String, String> params = new HashMap<>();
        params.put("id", Id);

        HttpFactory.onCreate().doGet(Urls.BOKEXIANGQING, params, myCallBack);
        Log.i("tag", Urls.BOKEXIANGQING);
    }

    @Override
    public void Exercise_Xiangqing(String Id, MyCallBack myCallBack) {
        Map<String, String> params = new HashMap<>();
        params.put("id", Id);

        HttpFactory.onCreate().doGet(Urls.BOKEXIANGQING, params, myCallBack);
        Log.i("tag", Urls.BOKEXIANGQING);
    }

    @Override
    public void Wenda__Xiangqing(String Id, MyCallBack myCallBack) {
        Map<String, String> params = new HashMap<>();
        params.put("id", Id);

        HttpFactory.onCreate().doGet(Urls.WENDAXIANGQING, params, myCallBack);
        Log.i("tag", Urls.WENDAXIANGQING);
    }

    /**
     *
     * @param pageIndex
     * @param myCallBack
     * 线下活动
     */
    @Override
    public void HuoDong(String pageIndex, MyCallBack myCallBack) {

        Map<String, String> params = new HashMap<>();
        params.put("uid", "0");
        params.put("pageIndex", pageIndex);
        params.put("pageSize", "10");

        HttpFactory.onCreate().doGet(Urls.HUODONG, params, myCallBack);
        Log.i("tag", Urls.HUODONG);


    }

    @Override
    public void HD_XiangQing(String Id, MyCallBack myCallBack) {

        Map<String, String> params = new HashMap<>();
        params.put("id", Id);

        HttpFactory.onCreate().doGet(Urls.HD_XIANGQING, params, myCallBack);
        Log.i("tag", Urls.HD_XIANGQING);

    }

    /**
     * 登录
     * @param username
     * @param psw
     * @param myCallBack
     */
    @Override
    public void login(String username, String psw,  MyCallBack myCallBack) {
        Map<String, String> pramass = new HashMap<>();
        pramass.put("username", username);
        pramass.put("pwd", psw);
        pramass.put("keep_login", "1");

        OkhttpUtils.getOkhttpUtils().doPost(Urls.LOGIN , pramass , myCallBack);
    }
}
