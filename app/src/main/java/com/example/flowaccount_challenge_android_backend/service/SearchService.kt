package com.example.flowaccount_challenge_android_backend.service

import android.util.Log
import com.example.flowaccount_challenge_android_backend.model.SearchModel
import com.google.gson.Gson
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchService {

    fun getSearch(textSearch: String, count: Int): Single<ArrayList<SearchModel.Items>> {
        return Single.create {

            val service = BuildServiceRetrofit.create().getSearch(textSearch, count)
            service.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    it.onError(t)
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val res = response.body()?.string()
                        Log.d("<S","======>$res")
                        val model = Gson().fromJson(res!!, SearchModel::class.java)
                        it.onSuccess(model.items)
                    } else {
                        it.onError(Exception(response.errorBody()?.string()))
                    }
                }

            })
        }
    }

}