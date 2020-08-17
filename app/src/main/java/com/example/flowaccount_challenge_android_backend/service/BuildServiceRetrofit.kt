package com.example.flowaccount_challenge_android_backend.service

import retrofit2.Retrofit

object BuildServiceRetrofit {

    fun create(): Service {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build()

        return retrofit.create(Service::class.java)
    }
}