package com.example.flowaccount_challenge_android_backend.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("search/repositories")
    fun getSearch(
        @Query("q") textSearch: String,
        @Query("per_page") count: Int
    ): Call<ResponseBody>
}