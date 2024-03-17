package com.example.apisession.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitReconnection {
    private static Retrofit retrofit;

    // Synchronized method to ensure thread safety
    private static synchronized Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiCalls getInstance() {
        return getRetrofit().create(ApiCalls.class);
    }
}

