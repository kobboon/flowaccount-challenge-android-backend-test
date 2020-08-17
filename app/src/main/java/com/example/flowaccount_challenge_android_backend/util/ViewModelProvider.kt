package com.example.flowaccount_challenge_android_backend.util

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelProvider : ViewModelProvider.NewInstanceFactory() {

    companion object {

        val viewModelsArray = ArrayList<ViewModel>()
        var viewModel: ViewModel? = null

        fun <T : ViewModel> getType(className: Class<T>): ViewModel {
            if (viewModelsArray.size != 0) {
                try {
                    viewModel = viewModelsArray.filter { viewModel ->
                        return@filter viewModel.javaClass == className
                    }.single()
                } catch (e: Exception) {
                    Log.d("<S", "viewModel : null")
                }
            }
            return if (viewModel == null) {
                val model = className.newInstance() as ViewModel
                viewModelsArray.add(model)
                model
            } else {
                viewModel!!
            }
        }
    }
}