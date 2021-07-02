package com.sony.store.myapplication.adapter.viewholder

import android.text.style.BackgroundColorSpan
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sony.store.myapplication.model.Item
import com.sony.store.myapplication.utils.LoadImage
import kotlinx.android.synthetic.main.item_3_big_banner.view.*

class Home3BigBannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bindTo(backgroundImage:String,_3BigBannerData: List<Item>){
        Glide.with(itemView.ivb).load(LoadImage.imageUrl(backgroundImage)).into(itemView.ivb)
        Glide.with(itemView.iv1).load(LoadImage.imageUrl(_3BigBannerData[0].image)).into(itemView.iv1)
        Glide.with(itemView.iv2).load(LoadImage.imageUrl(_3BigBannerData[1].image)).into(itemView.iv2)
        Glide.with(itemView.iv3).load(LoadImage.imageUrl(_3BigBannerData[2].image)).into(itemView.iv3)

    }


}