package com.example.administrator.os_china.model.http.biz.message.Main;

import com.example.administrator.os_china.model.http.callback.MyCallBack;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public interface IMain {

    void TYT(String uid ,String msg, MyCallBack myCallBack);

    void login(String username , String psw , MyCallBack myCallBack);
}
