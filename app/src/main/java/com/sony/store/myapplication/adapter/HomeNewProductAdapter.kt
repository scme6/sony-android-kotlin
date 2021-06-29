package com.sony.store.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.sony.store.myapplication.R
import com.sony.store.myapplication.adapter.viewholder.BannerViewHolder
import com.youth.banner.adapter.BannerAdapter

class HomeNewProductAdapter(datas: MutableList<String>) : BannerAdapter<String, BannerViewHolder>(datas) {
    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        return BannerViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_new_product_carousel_child,parent,false)).apply {

        }
    }

    override fun onBindView(holder: BannerViewHolder?, data: String?, position: Int, size: Int) {
    }
}