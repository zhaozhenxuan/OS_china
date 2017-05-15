package com.example.administrator.os_china.model.http.callback;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public interface IHttp {
    void  doPost(String url, Map<String,String> params, MyCallBack callBack);
    void  doGet(String url, Map<String,String> params,MyCallBack callBack);
}
