package com.example.flowaccount_challenge_android_backend.model

class SearchModel {
    var total_count: Int = 0
    var items: ArrayList<Items> = arrayListOf()

    class Items {
        lateinit var id: String
        lateinit var full_name: String
         var description: String? = null
        lateinit var owner: Owner

        class Owner {
            var avatar_url: String? = null
        }
    }
}