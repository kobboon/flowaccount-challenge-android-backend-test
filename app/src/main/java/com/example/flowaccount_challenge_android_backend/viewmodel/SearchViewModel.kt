package com.example.flowaccount_challenge_android_backend.viewmodel

import androidx.lifecycle.ViewModel
import com.example.flowaccount_challenge_android_backend.model.SearchModel
import com.example.flowaccount_challenge_android_backend.service.SearchService
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

class SearchViewModel : ViewModel() {

    private val service = SearchService()


    private val listSearch: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    fun getSearch(textSearch: String, count: Int): Single<ArrayList<SearchModel.Items>> {
        return service.getSearch(textSearch, count)
    }

}