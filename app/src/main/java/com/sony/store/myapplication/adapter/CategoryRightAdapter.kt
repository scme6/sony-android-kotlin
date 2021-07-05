package com.sony.store.myapplication.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sony.store.myapplication.ui.fragment.CategoryRightFragment


/**
 * 产品也 右侧
 */
class CategoryRightAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
      return 20
    }

    override fun createFragment(position: Int): Fragment {
     return CategoryRightFragment()
    }
}



//RecyclerView.Adapter<CategoryRightViewHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryRightViewHolder {
//        return CategoryRightViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.item_category_right, parent, false)
//        )
//    }
//
//    override fun getItemCount(): Int {
//        return 20
//    }
//
//    override fun onBindViewHolder(holder: CategoryRightViewHolder, position: Int) {
//        holder.bindTo()
//    }
//
//}