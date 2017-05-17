package com.example.administrator.os_china.utils;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public class Urls {
    //s总接口
    public static final String BASEUSER = "http://www.oschina.net/";
    //资讯
    public static final String ZIXUN = BASEUSER + "action/api/news_list";
    //新闻详情
    public static final String XINWEN = BASEUSER + "action/api/news_detail";
    //热点
    public static final String REDIAN = BASEUSER + "action/api/news_list";
    //博客

    public static final String BOKE = BASEUSER + "action/api/blog_list";
    //博客详情
    public static final String BOKEXIANGQING = BASEUSER + "action/api/blog_detail";
    //推荐
    public static final String TUIJIAN = BASEUSER + "action/api/blog_list";


    /**
     * 技术问答
     */

    //技术问答BaseUrl
    public static final String JISHUBASEURL = "http://www.oschina.net/";

    //提问
    public static final String TIWEN = JISHUBASEURL + "action/api/post_list";

    //问答详情
    public static final String WENDAXIANGQING = JISHUBASEURL + "action/api/post_detail";


    /**
     * 博客区
     */

    //博客BaseUrl
    public static final String BOKEBASEURL = "http://www.oschina.net/";
    //最新博客
    public static final String ZUIXIN = BOKEBASEURL + "action/api/blog_list";

}
