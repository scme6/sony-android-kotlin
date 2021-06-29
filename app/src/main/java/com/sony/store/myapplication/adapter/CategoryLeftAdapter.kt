package com.sony.store.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sony.store.myapplication.R
import com.sony.store.myapplication.adapter.viewholder.CategoryLeftViewHolder
import kotlinx.android.synthetic.main.item_category_left.view.*

class CategoryLeftAdapter : RecyclerView.Adapter<CategoryLeftViewHolder>() {

    private var onItemClickListener: ((position: Int) -> Unit)? = null

    private var selectPosition = 0
    fun changeSelectPosition(position: Int) {
        this.selectPosition = position
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(block: (position: Int) -> Unit) {
        this.onItemClickListener = block
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryLeftViewHolder {
        return CategoryLeftViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category_left, parent, false)
        ).apply {
            itemView.text.setOnClickListener {
                onItemClickListener?.invoke(adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: CategoryLeftViewHolder, position: Int) {
        if (selectPosition == position) {
            holder.itemView.itemRootView.setBackgroundResource(R.color.day_night_cate_left_itembg)
        } else {
            holder.itemView.itemRootView.setBackgroundResource(R.color.white)
        }
    }
}