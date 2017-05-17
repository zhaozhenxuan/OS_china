package com.example.administrator.os_china.model.http.biz.message;

import com.example.administrator.os_china.model.http.callback.MyCallBack;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public interface INewsModel {

    void NewsList(String pageIndex , MyCallBack myCallBack);

    void Blogs_Tuijian_List(String pageIndex , MyCallBack myCallBack);

    void AnswersList(String pageIndex , MyCallBack myCallBack);

    void ZuixinList(String pageIndex , MyCallBack myCallBack);

    void Newslist_Xiangqing(String Id , MyCallBack myCallBack);

    void Blogs_Xiangqing(String Id , MyCallBack myCallBack);

    void Exercise_Xiangqing(String Id , MyCallBack myCallBack);

    void Wenda__Xiangqing(String Id , MyCallBack myCallBack);

}
