package com.sony.store.myapplication.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sony.store.myapplication.model.Item
import com.sony.store.myapplication.utils.LoadImage
import kotlinx.android.synthetic.main.item_4_big_banner.view.*

class Home4BigBannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bindTo(backgroundImage: String, menuData: List<Item>) {
        Glide.with(itemView.ivb).load(backgroundImage).into(itemView.ivb)
        Glide.with(itemView.iv1).load(LoadImage.imageUrl(menuData[0].image)).into(itemView.iv1)
        Glide.with(itemView.iv2).load(LoadImage.imageUrl(menuData[1].image)).into(itemView.iv2)
        Glide.with(itemView.iv3).load(LoadImage.imageUrl(menuData[2].image)).into(itemView.iv3)
        Glide.with(itemView.iv4).load(LoadImage.imageUrl(menuData[3].image)).into(itemView.iv4)
    }



}