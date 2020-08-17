package com.example.flowaccount_challenge_android_backend.model

class SearchModel {

    var items: ArrayList<Items> = arrayListOf()

    class Items {
        lateinit var full_name: String
        lateinit var description: String
        lateinit var owner: Owner

        class Owner {
            var avatar_url: String? = null
        }
    }


}