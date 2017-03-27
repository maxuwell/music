package com.example.day38_musicplayerhomework;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by NYR on 2016/10/26.
 */
public class MyApp extends Application {
    private MyApp app;
    @Override
    public void onCreate() {
        super.onCreate();
        this.app = this;

        initOkHttp();
    }

    //初始化okHttpUtils
    private void initOkHttp() {
/**
 * 导包
 *  model 中添加   compile 'com.zhy:okhttputils:2.6.2'
 */
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
//其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
