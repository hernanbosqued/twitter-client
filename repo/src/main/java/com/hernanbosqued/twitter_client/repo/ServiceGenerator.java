package com.hernanbosqued.twitter_client.repo;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.annotation.Annotation;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class ServiceGenerator {
    private static ServiceGenerator instance;
    private final Retrofit retrofit;

    private ServiceGenerator() {
        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient client = new OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Constants.BASE_API)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    static ServiceGenerator getInstance() {
        if (instance == null) {
            instance = new ServiceGenerator();
        }
        return instance;
    }

    <T> T createService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }

    <T> T parseResponse(Class<T> type, ResponseBody responseBody) {
        Converter<ResponseBody, T> responseBodyObjectConverter = retrofit.responseBodyConverter(type, new Annotation[0]);
        try {
            return responseBodyObjectConverter.convert(responseBody);
        } catch (Exception err) {
            return null;
        }
    }
}