package com.example.administrator.os_china.model.http.callback;

import com.example.administrator.os_china.model.http.volley.VolleyUtils;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public class HttpFactory {

    private static final int OKHTTP = 0;
    private static final int VOLLEY = 1;
    private static final int RETROFIT = 2;
    private static final int TYPE = VOLLEY;


    public static IHttp onCreate() {
        IHttp iHttp = null;

        switch (TYPE) {
            case VOLLEY:
                iHttp = VolleyUtils.getInstence();
                break;
            case OKHTTP:
                break;
            case RETROFIT:
                break;

        }
        return iHttp;


    }
}
