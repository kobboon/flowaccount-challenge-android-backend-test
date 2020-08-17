package com.example.flowaccount_challenge_android_backend

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowaccount_challenge_android_backend.adapter.ItemAdapter
import com.example.flowaccount_challenge_android_backend.util.ViewModelProvider
import com.example.flowaccount_challenge_android_backend.viewmodel.SearchViewModel
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.plugins.RxJavaPlugins
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
    private lateinit var manager: LinearLayoutManager
    private var doubleCallAPI: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)
        recyclerView.addItemDecoration(itemDecorator)
        manager = LinearLayoutManager(this)
        recyclerView.layoutManager = manager

        adapter = ItemAdapter(this)
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(scrollListener)
        getSearch()
    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
        recyclerView.removeOnScrollListener(scrollListener)
    }

    private var scrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            val totalItemCount = recyclerView.layoutManager?.itemCount
            val firstVisible = manager.findLastVisibleItemPosition()
            if (totalItemCount == (firstVisible + 1)) {
                Log.d("<S", "========onScrollStateChanged======>$doubleCallAPI")
                if (doubleCallAPI) {
                    doubleCallAPI = false
                    Handler().postDelayed({ searchViewModel.setOnPage() }, 1000)
                }
            }
        }

    }

    private fun getSearch() {
        RxTextView.textChanges(edtSearch)
            .map(CharSequence::toString)
            .debounce(1000, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .flatMap { txt ->
                searchViewModel.resetCount()
                return@flatMap searchViewModel.countSearch()
                    .flatMap { count ->
                        return@flatMap searchViewModel.getSearch(txt, count).toObservable()
                    }
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    doubleCallAPI = true
                    Log.d("<S", "========hhhhh======>${it.size}")
                    adapter.setData(it)
                },
                onError = {
                    it.printStackTrace()
                }
            )
            .addTo(compositeDisposable)
    }
}