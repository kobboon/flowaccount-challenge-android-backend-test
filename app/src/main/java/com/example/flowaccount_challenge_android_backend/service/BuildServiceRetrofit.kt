package com.example.flowaccount_challenge_android_backend.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object BuildServiceRetrofit {

    fun create(): Service {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create<Service>(Service::class.java)
    }
}