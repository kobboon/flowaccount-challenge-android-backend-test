package com.example.flowaccount_challenge_android_backend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.flowaccount_challenge_android_backend.adapter.ItemAdapter
import com.example.flowaccount_challenge_android_backend.util.ViewModelProvider
import com.example.flowaccount_challenge_android_backend.viewmodel.SearchViewModel
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {


    private val searchViewModel by lazy {
        ViewModelProvider.getType(SearchViewModel::class.java) as SearchViewModel
    }

    private lateinit var adapter: ItemAdapter
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)
        recyclerView.addItemDecoration(itemDecorator)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        adapter = ItemAdapter(this)
        recyclerView.adapter = adapter

        getSearch()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun getSearch() {
        RxTextView.textChanges(edtSearch)
            .map(CharSequence::toString)
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .flatMap {
                searchViewModel.getSearch(it).toObservable()
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    Log.d("<S","=========onNext=========>")
                    adapter.setData(it)
                }
                , onError = {
                    it.printStackTrace()
                }
            )
            .addTo(compositeDisposable)
    }
}