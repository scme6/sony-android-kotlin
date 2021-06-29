package com.sony.store.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.sony.store.myapplication.R
import com.sony.store.myapplication.adapter.CategoryLeftAdapter
import com.sony.store.myapplication.adapter.CategoryRightAdapter
import com.sony.store.myapplication.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * @Description 一级 产品模块
 * @author: qiang
 * @date: 2021-06-28
 */
class CategoryFragment : BaseFragment() {
    override fun setContentView(): Int {
        return R.layout.fragment_category
    }

    override fun initView(view: View) {
        val categoryLeftAdapter = CategoryLeftAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = categoryLeftAdapter
        }
        val categoryRightAdapter = CategoryRightAdapter()
        viewPager2.apply {
            orientation = ViewPager2.ORIENTATION_VERTICAL
            adapter = categoryRightAdapter
        }
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                categoryLeftAdapter.changeSelectPosition(position)
            }
        })
        categoryLeftAdapter.setOnItemClickListener {
            viewPager2.setCurrentItem(it, false)
        }
    }


}