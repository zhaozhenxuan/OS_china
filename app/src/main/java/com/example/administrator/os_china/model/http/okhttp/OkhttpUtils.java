package com.example.administrator.os_china.model.http.okhttp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.administrator.os_china.App;
import com.example.administrator.os_china.model.http.callback.IMyHttp;
import com.example.administrator.os_china.model.http.callback.MyCallBack;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static retrofit2.Response.success;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public class OkhttpUtils implements IMyHttp {

    private static OkhttpUtils okhttpUtils = new OkhttpUtils();
    private OkHttpClient okHttpClient;

    private OkhttpUtils(){
        okHttpClient= new OkHttpClient.Builder().build();
    };

    public static OkhttpUtils getOkhttpUtils(){
        return okhttpUtils;
    }


    @Override
    public void doPost(String url, Map<String, String> params, final MyCallBack callBack) {
        if (url == null)
            return;

        FormBody.Builder builder = null;
        if (params != null && params.size() > 0) {
            builder = new FormBody.Builder();
            Set<String> keySet = params.keySet();
            for (String s : keySet) {
                String value = params.get(s);
                builder.add(s, value);
            }
        }

        Request request = new Request.Builder().url(url).post(builder.build()).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    keepCookie(response);
                    try {
                        final String string = response.body().string();
                        App.activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onSuccess(string);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void doGet(String url, Map<String, String> params, final MyCallBack callBack) {
        if (url == null)
            return;
        StringBuffer sb = null;
        if (params != null && params.size() > 0) {
            int count = 1;
            sb = new StringBuffer();
            sb.append("?");
            Set<String> key = params.keySet();
            for (String s : key) {
                String value = params.get(s);
                sb.append(s + "=" + value);
                if (count < key.size()) {
                    sb.append("&");
                }
                count++;
            }
        }
        String url1 = url.trim() + sb.toString();

        Request request = new Request.Builder().url(url1).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack .onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    try {
                        final String string = response.body().string();
                        App.activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onSuccess(string);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    @Override
    public void MyPost(String url, Map<String, String> params, final MyCallBack callBack) {
        if (url == null)
            return;

        FormBody.Builder builder = null;
        if (params != null && params.size() > 0) {
            builder = new FormBody.Builder();
            Set<String> keySet = params.keySet();
            for (String s : keySet) {
                String value = params.get(s);
                builder.add(s, value);
            }
        }

        String cookie = App.activity.getSharedPreferences("data", Context.MODE_PRIVATE).getString("cookie", "");
        Request request = new Request.Builder().addHeader("Cookie", cookie).url(url).post(builder.build()).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    try {
                        final String string = response.body().string();
                        App.activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onSuccess(string);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void MyGet(String url, Map<String, String> params, final MyCallBack callBack) {
        if (url == null)
            return;
        StringBuffer sb = null;
        if (params != null && params.size() > 0) {
            int count = 1;
            sb = new StringBuffer();
            sb.append("?");
            Set<String> key = params.keySet();
            for (String s : key) {
                String value = params.get(s);
                sb.append(s + "=" + value);
                if (count < key.size()) {
                    sb.append("&");
                }
                count++;
            }
        }
        String url1 = url.trim() + sb.toString();

        String cookie = App.activity.getSharedPreferences("data", Context.MODE_PRIVATE).getString("cookie", "");

        Request request = new Request.Builder().addHeader("Cookie", cookie).url(url1).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    try {
                        final String string = response.body().string();
                        App.activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onSuccess(string);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 保存cookie
     *
     * @param response
     */
    private void keepCookie(Response response) {
        String cookie = "";
        Headers headers = response.headers();
        Set<String> names = headers.names();
        for (String key : names) {
            String values = headers.get(key);
            if (key.contains("Set-Cookie"))
                cookie += values + ";";
            Log.i("kye---",key);
            Log.i("values---",values);
        }

        if (cookie.length() > 0) {
            cookie = cookie.substring(0, cookie.length() - 1);
        }

        Log.i("cookie", cookie);
        SharedPreferences data = App.activity.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = data.edit();
        edit.putString("cookie", cookie);
        edit.commit();
    }

}
