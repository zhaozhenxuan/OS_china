package com.example.administrator.os_china.model.http.biz.message.ruanjian;

import com.example.administrator.os_china.model.http.callback.MyCallBack;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public interface IRuanJian {

    void fenlei_one(MyCallBack myCallBack);

    void fenlei_two(String tag , MyCallBack myCallBack);

    void RJ_tuijian(String pageIndex , MyCallBack myCallBack);

}
