package com.example.flowaccount_challenge_android_backend.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.flowaccount_challenge_android_backend.model.SearchModel
import com.example.flowaccount_challenge_android_backend.service.SearchService
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

class SearchViewModel : ViewModel() {

    private val service = SearchService()

    private var isMore: Boolean = true

    fun getSearch(textSearch: String, count: Int): Observable<ArrayList<SearchModel.Items>> {
        return if (textSearch != "") {
            service.getSearch(textSearch, count).map {
                return@map it.items
            }
        } else {
            Observable.just(arrayListOf())
        }

    }


    fun resetCount() {
        isMore = true
    }

}