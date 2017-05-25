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


    /**
     * 动弹区
     */
    //最新动弹
    public static final String ZUIXINDT = BASEUSER + "action/api/tweet_list";

    //热门动弹
    public static final String REMENDT = BASEUSER + "action/api/tweet_list";

    //我的动弹
    public static final String MINEDT = BASEUSER + "action/api/tweet_list";

    /**
     * 活动区
     */
    public static final String HD_BASEURL = "http://www.oschina.net/";

    //活动
    public static final String HUODONG = HD_BASEURL + "action/api/event_list";

    //活动详情
    public static final String HD_XIANGQING = HD_BASEURL + "action/api/post_detail";

    /**
     * 开源软件区
     */

    //一级分类
    public static final String fenlei_one = BASEUSER + "action/api/softwarecatalog_list";

    //二级分类
    public static final String fenlei_two = BASEUSER + "action/api/softwarecatalog_list";

    //推荐
    public static final String RJ_tuijian = BASEUSER + "action/api/software_list";

    //三级分类
    public static final String fenlei_three = BASEUSER + "action/api/softwaretag_list";

    //最新
    public static final String RJ_zuixin = BASEUSER + "action/api/software_list";

    //热门
    public static final String RJ_remen = BASEUSER + "action/api/software_list";

    //国产
    public static final String RJ_guochan = BASEUSER + "action/api/software_list";

    /**
     * 登录有关
     */

    //登录
    public static final String LOGIN = BASEUSER + "action/api/login_validate";


}
