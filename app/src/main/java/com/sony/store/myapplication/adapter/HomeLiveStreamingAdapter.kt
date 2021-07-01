package com.sony.store.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sony.store.myapplication.R
import com.sony.store.myapplication.adapter.viewholder.HomeLiveStreamingChildViewHolder

class HomeLiveStreamingAdapter : RecyclerView.Adapter<HomeLiveStreamingChildViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeLiveStreamingChildViewHolder {
        return HomeLiveStreamingChildViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_live_streaming_child, parent, false)
        )
    }

    override fun getItemCount(): Int {
       return 2
    }

    override fun onBindViewHolder(holder: HomeLiveStreamingChildViewHolder, position: Int) {
    }
}