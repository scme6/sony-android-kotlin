package com.sony.store.myapplication.adapter.viewholder

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.sony.store.myapplication.adapter.FeedsFragmentAdapter
import kotlinx.android.synthetic.main.item_main_feeds.view.*

class FeedsViewHolder(itemView: View, mFragment: Fragment) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.main_feeds_view_pager.adapter = FeedsFragmentAdapter(mFragment)
        itemView.tabLayout.setViewPager(itemView.main_feeds_view_pager)
    }

    fun bind() {

    }


}