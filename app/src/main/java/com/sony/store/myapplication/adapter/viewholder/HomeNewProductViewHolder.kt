package com.sony.store.myapplication.adapter.viewholder

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.sony.store.myapplication.adapter.HomeNewProductAdapter
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.listener.OnPageChangeListener
import kotlinx.android.synthetic.main.item_new_product_carousel.view.*

class HomeNewProductViewHolder(itemView: View, private val lifecycleOwner: LifecycleOwner) :
    RecyclerView.ViewHolder(itemView) {

    fun bindTo() {
        itemView.banner.addBannerLifecycleObserver(lifecycleOwner)//添加生命周期观察者
            .setAdapter(HomeNewProductAdapter(mutableListOf("", "")))
            .setIndicator(CircleIndicator(itemView.context))
        itemView.banner.addOnPageChangeListener(object : OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
            override fun onPageSelected(position: Int) {
                //设置滚动条移动
                if (position == 0)
                    itemView.main_line.translationX = 0f
                if (position == 1) {
                    itemView.main_line.translationX =
                        (itemView.parent_layout.width - itemView.main_line.width).toFloat()
                }
            }
        })


    }
}