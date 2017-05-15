package com.example.administrator.os_china.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public class ThreadUtils {

    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static void runOnUiThread(Runnable runnable){
        mHandler.post(runnable);
    }

    public static void runStubThread(Runnable runnable){
        new Thread(runnable).start();
    }

}
