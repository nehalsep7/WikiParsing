package com.example.nehal.wikiparsing;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nehal on 28/7/17.
 */

public class AppUtils {

    public static Api getWikiRetrofitApi(Context context) {
        return getRetrofitApi(getV2BaseURL(), context);
    }
    public static Api getRetrofitApi(String url, final Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        return api;
    }
    public static String getV2BaseURL() {
        return BuildConfig.SERVER_URL;
    }
}
