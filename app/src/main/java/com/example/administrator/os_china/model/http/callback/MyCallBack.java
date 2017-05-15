package com.example.administrator.os_china.model.http.callback;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public interface MyCallBack {
    //成功的回调
    void  onSuccess(String result);
    //失败的回调
    void  onError(String MsgError);
}
