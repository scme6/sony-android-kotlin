package com.sony.store.myapplication.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sony.store.myapplication.model.Item
import com.sony.store.myapplication.utils.LoadImage
import kotlinx.android.synthetic.main.item_notice.view.*

/**
 * @Description 首页中 通知 item
 * @author: qiang
 * @date: 2021-06-27
 */
class NoticeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bindTo(menuData: List<Item>) {
        Glide.with(itemView.iv).load(LoadImage.imageUrl(menuData[0].image)).into(itemView.iv)
        itemView.tv.text = menuData[0].title
    }
}