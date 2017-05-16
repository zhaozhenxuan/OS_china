package com.example.administrator.os_china.model.http.volley;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.os_china.App;
import com.example.administrator.os_china.model.http.callback.IHttp;
import com.example.administrator.os_china.model.http.callback.MyCallBack;

import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public class VolleyUtils implements IHttp {


    private static VolleyUtils volleyUtils = new VolleyUtils();

    private VolleyUtils() {
    }


    public static VolleyUtils getInstence() {
        return volleyUtils;
    }


    @Override
    public void doPost(String url, final Map<String, String> params, final MyCallBack callBack) {
        RequestQueue queue = Volley.newRequestQueue(App.activity);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("TAG", "请求成功 :" + s.toString());
                callBack.onSuccess(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("TAG", "请求失败 :" + volleyError);
                callBack.onError(volleyError.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        queue.add(stringRequest);

    }

    @Override
    public void doGet(String url, final Map<String, String> params, final MyCallBack callBack) {
        if (params != null && params.size() > 0) {
            StringBuffer sb = new StringBuffer(url);
            sb.append("?");
            Set<String> set = params.keySet();
            for (String s : set) {
                sb.append(s).append("=").append(params.get(s)).append("&");
            }
            url = sb.toString().substring(0, sb.length() - 1);
        }


        RequestQueue queue = Volley.newRequestQueue(App.activity);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("TAG", "请求成功 :" + s.toString());
                callBack.onSuccess(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("TAG", "请求失败 :" + volleyError);
                callBack.onError(volleyError.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        queue.add(stringRequest);


    }
}



