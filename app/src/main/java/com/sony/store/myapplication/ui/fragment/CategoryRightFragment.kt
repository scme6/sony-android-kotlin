package com.sony.store.myapplication.ui.fragment

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sony.store.myapplication.R
import com.sony.store.myapplication.adapter.FeedsListAdapter
import com.sony.store.myapplication.base.BaseFragment
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