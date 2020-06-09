package com.priyank_py.i2e1task.network;

import com.priyank_py.i2e1task.Utility;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    private static Retrofit usersRetrofit = new Retrofit.Builder()
            .baseUrl(Utility.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S createService(Class<S> serviceClass) {
        return usersRetrofit.create(serviceClass);
    }
}
