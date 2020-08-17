package com.example.flowaccount_challenge_android_backend.service

import android.util.Log
import com.example.flowaccount_challenge_android_backend.model.SearchModel
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SearchService {

//    fun getSearch(textSearch: String, count: Int): Single<SearchModel> {
//        return Single.create {
//
////            val service = BuildServiceRetrofit.create().getSearch("$textSearch&per_page=$count")
//            val service = BuildServiceRetrofit.create().getSearch(textSearch, count)
//            Log.d("<S", "======>${service.request().url()}")
//            service.enqueue(object : Callback<ResponseBody> {
//                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                    it.onError(t)
//                }
//
//                override fun onResponse(
//                    call: Call<ResponseBody>,
//                    response: Response<ResponseBody>
//                ) {
//                    if (response.isSuccessful) {
//                        val res = response.body()?.string()
//                        Log.d("<S", "======>$res")
//                        val model = Gson().fromJson(res!!, SearchModel::class.java)
//                        it.onSuccess(model)
//                    } else {
//                        it.onError(Exception(response.errorBody()?.string()))
//                    }
//                }
//
//            })
//        }
//    }

    fun getSearch(textSearch: String, count: Int): Observable<SearchModel> {
        return BuildServiceRetrofit.create()
            .getSearch(textSearch, count)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).map {
                val res = it.string()
                Log.d("<S", "======>$res")
                val model = Gson().fromJson(res, SearchModel::class.java)
               return@map model

            }
//                .subscribe(object : Observer<ResponseBody> {
//                    override fun onComplete() {
//
//                    }
//
//                    override fun onSubscribe(d: Disposable) {
//
//                    }
//
//                    override fun onNext(response: ResponseBody) {
//                        val res = response.string()
//                        Log.d("<S", "======>$res")
//                        val model = Gson().fromJson(res!!, SearchModel::class.java)
//                        it.(model)
//                    }
//
//                    override fun onError(e: Throwable) {
//                        it.onError(Exception(response.errorBody()?.string()))
//                    }
//
//                })


    }

}