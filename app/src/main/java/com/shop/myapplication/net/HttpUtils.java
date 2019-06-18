package com.shop.myapplication.net;

import com.shop.myapplication.api.Api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 *@Auther:甄帅
 *@Date: 时间
 *@Description:功能
 * */public class HttpUtils {

    private static HttpUtils getInstance;
    private final Retrofit retrofit;

    private HttpUtils() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient).baseUrl(Api.BASE_URL).build();
    }

    public static HttpUtils getInstance() {

        if (getInstance == null) {
            synchronized (HttpUtils.class) {
                if (getInstance == null) {
                    getInstance = new HttpUtils();
                }
            }
        }
        return getInstance;
    }
    public <T>T creat(Class<T> clz){
        return retrofit.create(clz);
    }
}


