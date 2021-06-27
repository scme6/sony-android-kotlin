package com.sony.store.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sony.store.myapplication.R
import com.sony.store.myapplication.adapter.viewholder.HomeMenuListViewHolder
import com.sony.store.myapplication.utils.UIUtil.screenWidth

class HomeMenuAdapter : RecyclerView.Adapter<HomeMenuListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMenuListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_menu_child,
            parent,
            false
        ).apply {
            layoutParams = ViewGroup.LayoutParams(
                (screenWidth(context) / 5)-10,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        return HomeMenuListViewHolder(
            itemView
        )
    }

    override fun getItemCount(): Int {
        return 16
    }

    override fun onBindViewHolder(holder: HomeMenuListViewHolder, position: Int) {
    }
}