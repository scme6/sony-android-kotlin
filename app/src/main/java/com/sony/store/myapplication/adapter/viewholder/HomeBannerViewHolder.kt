package com.sony.store.myapplication.adapter.viewholder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sony.store.myapplication.model.BannerItems
import com.sony.store.myapplication.model.Item
import com.sony.store.myapplication.model.NewIndexModelItem
import com.sony.store.myapplication.utils.LoadImage.imageUrl
import kotlinx.android.synthetic.main.item_banner.view.*

class HomeBannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindTo(newIndexModelItem: List<Item>) {
        Glide.with(itemView.backgroundImage).load("").into(itemView.backgroundImage)

       val filterList=newIndexModelItem.filter {
            it.display == "true"
        }

        itemView.xbanner.setBannerData(filterList)
        itemView.xbanner.loadImage { banner, model, view, position ->
            view as ImageView
            model as Item
            Glide.with(itemView.backgroundImage).load(imageUrl(model.image))
                .into(view)
        }

    }

}