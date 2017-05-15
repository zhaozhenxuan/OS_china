package com.example.administrator.os_china.model.http.biz.message;

import com.example.administrator.os_china.model.http.callback.MyCallBack;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public interface INewsModel {

    void NewsList(String pageIndex , MyCallBack myCallBack);

    void BlogsList(String pageIndex , MyCallBack myCallBack);

    void AnswersList(String pageIndex , MyCallBack myCallBack);

    void ZuixinList(String pageIndex , MyCallBack myCallBack);

}
