package com.sony.store.myapplication.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sony.store.myapplication.model.Item
import com.sony.store.myapplication.utils.LoadImage
import kotlinx.android.synthetic.main.item_menu_child.view.*

class HomeMenuListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindTo(item: Item) {
        Glide.with(itemView.iv).load(LoadImage.imageUrl(item.image)).into(itemView.iv)
        itemView.tvName.text = item.title
    }


}