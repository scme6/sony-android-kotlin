package com.sony.store.myapplication.adapter.viewholder
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sony.store.myapplication.model.BannerItems
import kotlinx.android.synthetic.main.item_banner.view.*

class HomeBannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindTo() {
        Glide.with(itemView.backgroundImage).load("").into(itemView.backgroundImage)
        itemView.xbanner.setBannerData(
            listOf(
                BannerItems("content/dam/sonystyle/mobile/app_v6/kv/kv_1065x426/kv_xperia1m3_210615.jpg")
                ,
                BannerItems("content/dam/sonystyle/mobile/app_v6/kv/kv_1065x426/zv1_210622.jpg")
                ,
                BannerItems("content/dam/sonystyle/mobile/app_v6/kv/kv_1065x426/kv_bravia_homesite_210607.jpg")
                ,
                BannerItems("content/dam/sonystyle/mobile/app_v6/kv/kv_1065x426/a80j_210622.jpg")
            )
        )
        itemView.xbanner.loadImage { banner, model, view, position ->
            view as ImageView
            model as BannerItems
            Glide.with(itemView.backgroundImage).load("https://www.sonystyle.com.cn/${model.image}").into(view)
        }

    }

}