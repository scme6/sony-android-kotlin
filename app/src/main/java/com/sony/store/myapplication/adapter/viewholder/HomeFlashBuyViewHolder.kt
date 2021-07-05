package com.sony.store.myapplication.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sony.store.myapplication.adapter.HomeFlashBuyAdapter
import com.sony.store.myapplication.utils.LoadImage.imageUrl
import kotlinx.android.synthetic.main.item_flashbuy.view.*

class HomeFlashBuyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindTo() {
        Glide.with(itemView.iv)
            .load(imageUrl("content/dam/sonystyle/mobile/app_v6/icons/flashbuy_210x78/210x78_flashbuy1_210420.png"))
            .into(itemView.iv)
        val mAdapter = HomeFlashBuyAdapter()
        itemView.rcv.apply {
            layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = mAdapter
        }
    }
}