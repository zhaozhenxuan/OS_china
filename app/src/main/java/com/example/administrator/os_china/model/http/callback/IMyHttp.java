package com.example.administrator.os_china.model.http.callback;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public interface IMyHttp {


    void  doPost(String url, Map<String,String> params, MyCallBack callBack);
    void  doGet(String url, Map<String,String> params,MyCallBack callBack);

    void  MyPost(String url, Map<String,String> params,MyCallBack callBack);
    void  MyGet(String url, Map<String,String> params,MyCallBack callBack);
}
