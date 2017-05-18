package com.example.administrator.os_china.model.http.biz.message.dt;

import com.example.administrator.os_china.model.http.callback.HttpFactory;
import com.example.administrator.os_china.model.http.callback.MyCallBack;
import com.example.administrator.os_china.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class DtImpl implements IDt {

    /**
     *
     * @param pageIndex
     * @param myCallBack
     * 最新动弹业务实现类
     */
    @Override
    public void ZuixinDT(String pageIndex, MyCallBack myCallBack) {
        Map<String, String> params = new HashMap<>();
        params.put("uid", "0");
        params.put("pageIndex", pageIndex);
        params.put("pageSize", "20");

        HttpFactory.onCreate().doGet(Urls.ZUIXINDT, params, myCallBack);
    }

    /**
     *
     * @param pageIndex
     * @param myCallBack
     * 热门动弹业务实现类
     */
    @Override
    public void RemenDT(String pageIndex, MyCallBack myCallBack) {
        Map<String, String> params = new HashMap<>();
        params.put("uid", "-1");
        HttpFactory.onCreate().doGet(Urls.ZUIXINDT, params, myCallBack);
    }

    @Override
    public void MineDT(String pageIndex, MyCallBack myCallBack) {
        Map<String, String> params = new HashMap<>();
        params.put("uid", "1");
        HttpFactory.onCreate().doGet(Urls.ZUIXINDT, params, myCallBack);
    }


}
