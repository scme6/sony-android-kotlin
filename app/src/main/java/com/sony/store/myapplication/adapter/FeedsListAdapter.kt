package com.sony.store.myapplication.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sony.store.myapplication.R
import com.sony.store.myapplication.adapter.viewholder.FeedsListViewHolder

class FeedsListAdapter : RecyclerView.Adapter<FeedsListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedsListViewHolder {
        return FeedsListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_main,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 50
    }

    override fun onBindViewHolder(holder: FeedsListViewHolder, position: Int) {
    }
}