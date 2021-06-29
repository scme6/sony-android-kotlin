package com.sony.store.myapplication.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sony.store.myapplication.utils.LoadImage.imageUrl
import com.sony.store.myapplication.widget.viewpager.SimpleOverlayAdapter
import kotlinx.android.synthetic.main.item_sub_kv.view.*


class HomeSubKvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindTo() {
        val adapter = SimpleOverlayAdapter(itemView.context)
        adapter.setImgUrlsAndBindViewPager(
            itemView.vp_main, arrayOf(
                imageUrl("content/dam/sonystyle/mobile/app_v6/kv/kv_1065x426/promo_bravia_210624.jpg")
                ,
                imageUrl("content/dam/sonystyle/mobile/app_v6/kv/kv_1065x426/promo_lens210622.jpg")
                ,
                imageUrl("content/dam/sonystyle/mobile/app_v6/kv/kv_1065x426/zx505_210622.jpg")
            )
            , 3
        )
        itemView.vp_main.adapter = adapter
        itemView.vp_main.currentItem = 0

    }
}