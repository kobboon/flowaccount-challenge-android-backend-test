package com.example.flowaccount_challenge_android_backend.viewmodel

import androidx.lifecycle.ViewModel
import com.example.flowaccount_challenge_android_backend.model.SearchModel
import com.example.flowaccount_challenge_android_backend.service.SearchService
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

class SearchViewModel : ViewModel() {

    private val service = SearchService()

    private val countSearch: BehaviorSubject<Int> = BehaviorSubject.createDefault(100)
    private var count: Int = 100
    private var isMore: Boolean = true
    private var hhh: ArrayList<SearchModel.Items> = arrayListOf()

    fun getSearch(textSearch: String, count: Int): Single<ArrayList<SearchModel.Items>> {
        if (textSearch != "") {
            return service.getSearch(textSearch, count)
                .map {
                    isMore = this.count <= it.total_count
                    hhh = it.items
                    return@map it.items
                }
        } else {
            return Single.just(hhh)
        }
    }


    fun resetCount() {
        isMore = true
        count = 20
    }

    fun setOnPage() {
        if (isMore) {
            count += 20
            countSearch.onNext(count)
        }
    }

    fun countSearch(): Observable<Int> {
        return countSearch
    }

}