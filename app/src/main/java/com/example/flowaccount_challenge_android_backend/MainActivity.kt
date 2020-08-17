package com.example.flowaccount_challenge_android_backend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.flowaccount_challenge_android_backend.adapter.ItemAdapter
import com.example.flowaccount_challenge_android_backend.util.ViewModelProvider
import com.example.flowaccount_challenge_android_backend.viewmodel.SearchViewModel
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val searchViewModel by lazy {
        ViewModelProvider.getType(SearchViewModel::class.java) as SearchViewModel
    }

    private lateinit var adapter : ItemAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)
        recyclerView.addItemDecoration(itemDecorator)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)


        searchViewModel.getSearch("Android", 5)
            .subscribeBy(
                onSuccess = {
                    Log.d("<S", "=============> ${it.size}")
                }, onError = {
                    it.printStackTrace()
                }
            )

    }


}