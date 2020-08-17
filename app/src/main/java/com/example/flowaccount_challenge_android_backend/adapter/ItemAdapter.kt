package com.example.flowaccount_challenge_android_backend.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flowaccount_challenge_android_backend.R
import kotlinx.android.synthetic.main.list_item.view.*

class ItemAdapter(private var context: Context)  : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return  5
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    fun setData(){

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtName: TextView = view.txtName
        var txtDes: TextView = view.txtDes
        var img: ImageView = view.img
    }
}