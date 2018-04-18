package com.mayi.yun.teachsystem.network;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者： wh
 * 时间：  2018/3/10
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class RetrofitManager {
    private static final int WRITE_TIME = 10;
    private static final int READ_TIME = 10;
    private static final int CONNECT_TIME = 10;
    /**
     * 网络访问
     */
    public static OkHttpClient mOkHttpClient;

    public static   <T> T create(Class<T> tClass, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();
        return retrofit.create(tClass);
    }

    private static OkHttpClient getOkHttpClient() {
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("zcb", "OkHttp====Message:" + message);
            }
        });
        loggingInterceptor.setLevel(level);
        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(WRITE_TIME, TimeUnit.SECONDS)
                .readTimeout(READ_TIME, TimeUnit.SECONDS)
                .writeTimeout(CONNECT_TIME, TimeUnit.SECONDS)
                .build();
        return mOkHttpClient;
    }
}
