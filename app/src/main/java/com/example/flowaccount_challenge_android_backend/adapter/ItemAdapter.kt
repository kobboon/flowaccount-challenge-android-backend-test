package com.example.flowaccount_challenge_android_backend.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flowaccount_challenge_android_backend.R
import com.example.flowaccount_challenge_android_backend.model.SearchModel
import com.example.flowaccount_challenge_android_backend.util.ViewModelProvider
import com.example.flowaccount_challenge_android_backend.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.list_item.view.*

class ItemAdapter(private var context: Context) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

//    private val searchViewModel by lazy {
//        ViewModelProvider.getType(SearchViewModel::class.java) as SearchViewModel
//    }

    var list: ArrayList<SearchModel.Items> = arrayListOf()
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
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.img)
            .load(list[position].owner.avatar_url)
            .into(holder.img)
        holder.txtName.text = "${(position + 1)} " + list[position].full_name
        if ((position + 1) % 20 == 0) {
            holder.ln.setBackgroundColor(Color.parseColor("#1203DAC5"))
        } else {
            holder.ln.setBackgroundColor(Color.parseColor("#ffffff"))
        }
        holder.txtDes.text = list[position].description ?: ""
    }


    fun setData(list: ArrayList<SearchModel.Items>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }


    fun setDataNew(list: ArrayList<SearchModel.Items>) {
        this.list.clear()
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtName: TextView = view.txtName
        var txtDes: TextView = view.txtDes
        var img: ImageView = view.img
        var ln: LinearLayout = view.ln
    }
}