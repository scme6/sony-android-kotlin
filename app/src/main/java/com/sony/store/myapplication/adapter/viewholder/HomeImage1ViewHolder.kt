package com.sony.store.myapplication.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sony.store.myapplication.BASE_IMAGE_URL
import com.sony.store.myapplication.model.BannerItems
import com.sony.store.myapplication.model.Item
import com.sony.store.myapplication.utils.LoadImage
import kotlinx.android.synthetic.main.item_image_1.view.*

class HomeImage1ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bindTo(backgroundImage: String, _1BigBannerData: List<Item>) {
        if (_1BigBannerData.isEmpty()) return
        Glide.with(itemView.backgroundImage).load(backgroundImage).into(itemView.backgroundImage)
        Glide.with(itemView.image).load(LoadImage.imageUrl(_1BigBannerData[0].image))
            .into(itemView.image)
    }
}