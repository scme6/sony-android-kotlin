package com.sony.store.myapplication.ui

import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sony.store.myapplication.R
import com.sony.store.myapplication.adapter.FeedsListAdapter
import com.sony.store.myapplication.base.BaseFragment
import com.sony.store.myapplication.widget.recyclerview.RecyclerViewAtViewPager2
import com.stone.persistent.recyclerview.library.ChildRecyclerView
import kotlinx.android.synthetic.main.fragment_category_right.*

/**
 * 产品模块 右侧的fragment
 */
class CategoryRightFragment : BaseFragment() {


    private val categoryViewModel by activityViewModels<CategoryViewModel>()

    override fun setContentView(): Any {
        return R.layout.fragment_category_right
    }

    override fun initView(view: View) {
       recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = FeedsListAdapter()
        }
        smartRefreshLayout.setOnLoadMoreListener {
            categoryViewModel.plusViewPagerPosition()
        }
        smartRefreshLayout.setOnRefreshListener {
            categoryViewModel.minusViewPagerPosition()
        }
    }
}