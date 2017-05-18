package com.example.administrator.os_china.model.http.biz.message.dt;

import com.example.administrator.os_china.model.http.callback.MyCallBack;

/**
 * Created by Administrator on 2017/5/18 0018.
 * 动弹业务接口
 */

public interface IDt {

    void ZuixinDT(String pageIndex , MyCallBack myCallBack);

    void RemenDT(String pageIndex , MyCallBack myCallBack);

    void MineDT(String pageIndex , MyCallBack myCallBack);
}
