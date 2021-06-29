package com.sony.store.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sony.store.myapplication.R
import com.sony.store.myapplication.adapter.viewholder.HomeFlashBuyChildViewHolder

class HomeFlashBuyAdapter : RecyclerView.Adapter<HomeFlashBuyChildViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFlashBuyChildViewHolder {
        return HomeFlashBuyChildViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_flashbuy_child, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: HomeFlashBuyChildViewHolder, position: Int) {
    }
}