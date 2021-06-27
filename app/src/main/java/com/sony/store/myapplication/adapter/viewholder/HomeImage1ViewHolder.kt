package com.sony.store.myapplication.adapter.viewholder
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sony.store.myapplication.BASE_IMAGE_URL
import com.sony.store.myapplication.model.BannerItems
import kotlinx.android.synthetic.main.item_image_1.view.*

class HomeImage1ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bindTo() {
        val items =
            BannerItems("content/dam/sonystyle/mobile/app_v6/promotion_banner/banner_1125x300/bravia_promo_210622.png")
        Glide.with(itemView.backgroundImage).load("").into(itemView.backgroundImage)
        Glide.with(itemView.image).load("$BASE_IMAGE_URL${items.image}").into(itemView.image)

    }
}