package com.sony.store.myapplication.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.sony.store.myapplication.adapter.HomeLiveStreamingAdapter
import kotlinx.android.synthetic.main.item_live_streaming.view.*

class HomeLiveStreamingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindTo() {
        val mAdapter = HomeLiveStreamingAdapter()
        val pagerSnapHelper = PagerSnapHelper()
        itemView.recyclerView.onFlingListener=null
        pagerSnapHelper.attachToRecyclerView(itemView.recyclerView)
        itemView.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            adapter = mAdapter
            clipToPadding=false
            setPadding(0,0,18,0)
        }
    }

}