package com.sorrowdrug.gankiobyso.net;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chentaikang on 2017/5/29 00:37.
 */

public class NetUtils {

    private static final String BASE_URL="http://gank.io/api/";
    private static final String TAG="=====HTTP=====";

    private static NetUtils instance;

    private IApi mApi;
    private Retrofit retrofit;


    public static NetUtils getInstance(){
        if(instance ==null){
            synchronized (NetUtils.class){
                if(instance ==null){
                    instance = new NetUtils();
                }
            }
        }
        return instance;
    }

    private NetUtils(){
        //初始化Retrofit

        //设置网络LOG日志打印的拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i(TAG,message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public IApi getApi(){
        if(mApi == null){
            mApi = retrofit.create(IApi.class);
        }
        return mApi;
    }

}
