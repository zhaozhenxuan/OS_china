package com.example.administrator.os_china.model.http.biz.message.ruanjian;

import android.util.Log;

import com.example.administrator.os_china.model.http.callback.HttpFactory;
import com.example.administrator.os_china.model.http.callback.MyCallBack;
import com.example.administrator.os_china.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class RuanJianImpl implements IRuanJian {

    /**
     * 分类一级列表
     * @param myCallBack
     */
    @Override
    public void fenlei_one(MyCallBack myCallBack) {
        Map<String, String> params = new HashMap<>();
        params.put("type", "0");

        HttpFactory.onCreate().doGet(Urls.fenlei_one, params, myCallBack);
        Log.i("tag", Urls.fenlei_one);
    }

    /**
     * 分类二级列表
     * @param tag
     * @param myCallBack
     */
    @Override
    public void fenlei_two(String tag, MyCallBack myCallBack) {

        Map<String, String> params = new HashMap<>();
        params.put("tag", tag);

        HttpFactory.onCreate().doGet(Urls.fenlei_two, params, myCallBack);
        Log.i("tag", Urls.fenlei_two);

    }

    /**
     * 推荐
     * @param pageIndex
     * @param myCallBack
     */
    @Override
    public void RJ_tuijian(String pageIndex, MyCallBack myCallBack) {
        Map<String, String> params = new HashMap<>();
        params.put("searchTag", "recommend");
        params.put("pageIndex", pageIndex);
        params.put("pageSize", "20");

        HttpFactory.onCreate().doGet(Urls.RJ_tuijian, params, myCallBack);
        Log.i("tag", Urls.RJ_tuijian);
    }

    /**
     * 三级分类
     * @param searchTag
     * @param pageIndex
     * @param myCallBack
     */
    @Override
    public void fenlei_three(String searchTag, String pageIndex, MyCallBack myCallBack) {
        Map<String, String> params = new HashMap<>();
        params.put("searchTag", searchTag);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", "20");

        HttpFactory.onCreate().doGet(Urls.fenlei_three, params, myCallBack);
        Log.i("tag", Urls.fenlei_three);
    }

    /**
     * 最新
     * @param pageIndex
     * @param myCallBack
     */
    @Override
    public void RJ_zuixin(String pageIndex, MyCallBack myCallBack) {

        Map<String, String> params = new HashMap<>();
        params.put("searchTag", "time");
        params.put("pageIndex", pageIndex);
        params.put("pageSize", "20");

        HttpFactory.onCreate().doGet(Urls.RJ_zuixin, params, myCallBack);
        Log.i("tag", Urls.RJ_zuixin);
    }

    /**
     * 热门
     * @param pageIndex
     * @param myCallBack
     */
    @Override
    public void RJ_remen(String pageIndex, MyCallBack myCallBack) {
        Map<String, String> params = new HashMap<>();
        params.put("searchTag", "view");
        params.put("pageIndex", pageIndex);
        params.put("pageSize", "20");

        HttpFactory.onCreate().doGet(Urls.RJ_remen, params, myCallBack);
        Log.i("tag", Urls.RJ_remen);
    }

    /**
     * 国产
     * @param pageIndex
     * @param myCallBack
     */
    @Override
    public void RJ_guochan(String pageIndex, MyCallBack myCallBack) {
        Map<String, String> params = new HashMap<>();
        params.put("searchTag", "list_cn");
        params.put("pageIndex", pageIndex);
        params.put("pageSize", "20");

        HttpFactory.onCreate().doGet(Urls.RJ_guochan, params, myCallBack);
        Log.i("tag", Urls.RJ_guochan);
    }
}
