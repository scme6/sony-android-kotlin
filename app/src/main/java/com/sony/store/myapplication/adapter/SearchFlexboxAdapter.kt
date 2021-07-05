package com.sony.store.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sony.store.myapplication.R
import kotlinx.android.synthetic.main.item_flexbox.view.*

/**
 * 搜索页面中 浏览历史 推荐搜索等流式布局
 */
class SearchFlexboxAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list= emptyList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_flexbox, parent, false)
        ) {}
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.tv.text=list[position]

    }
}