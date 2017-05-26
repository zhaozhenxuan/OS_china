package com.example.administrator.os_china.model.http.biz.message.Main;

import com.example.administrator.os_china.model.http.callback.MyCallBack;
import com.example.administrator.os_china.model.http.okhttp.OkhttpUtils;
import com.example.administrator.os_china.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public class MainImpl implements IMain {

    @Override
    public void TYT(String uid, String msg, MyCallBack myCallBack) {
        Map<String, String> pramass = new HashMap<>();
        pramass.put("uid", uid);
        pramass.put("msg", msg);

        OkhttpUtils.getOkhttpUtils().MyPost(Urls.FABIAO_DT , pramass , myCallBack);
    }
}
