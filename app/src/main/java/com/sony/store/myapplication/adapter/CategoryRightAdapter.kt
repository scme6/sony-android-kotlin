package com.sony.store.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sony.store.myapplication.R
import com.sony.store.myapplication.adapter.viewholder.CategoryRightViewHolder

class CategoryRightAdapter : RecyclerView.Adapter<CategoryRightViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryRightViewHolder {
        return CategoryRightViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category_right, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: CategoryRightViewHolder, position: Int) {
    }

}