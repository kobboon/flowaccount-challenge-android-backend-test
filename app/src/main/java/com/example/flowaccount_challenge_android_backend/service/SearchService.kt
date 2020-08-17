package com.example.flowaccount_challenge_android_backend.service

import android.util.Log
import com.example.flowaccount_challenge_android_backend.model.SearchModel
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchService {

    fun getSearch(textSearch: String, count: Int): Observable<SearchModel> {
        return BuildServiceRetrofit.create()
            .getSearch(textSearch, count)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).map {
                val res = it.string()
//                Log.d("<S", "======>$res")
                val model = Gson().fromJson(res, SearchModel::class.java)
               return@map model

            }
    }

}