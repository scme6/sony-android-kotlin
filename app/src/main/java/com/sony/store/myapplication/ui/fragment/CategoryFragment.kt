package com.sony.store.myapplication.ui.fragment

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.sony.store.myapplication.R
import com.sony.store.myapplication.adapter.CategoryLeftAdapter
import com.sony.store.myapplication.adapter.CategoryRightAdapter
import com.sony.store.myapplication.base.BaseFragment
import com.sony.store.myapplication.ui.activity.SearchActivity
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.include_search.*

/**
 * @Description 一级 产品模块
 * @author: qiang
 * @date: 2021-06-28
 */
class CategoryFragment : BaseFragment() {



    private val categoryViewModel by activityViewModels<CategoryViewModel>()
    override fun init() {
    }

    override fun setContentView(): Int {
        return R.layout.fragment_category
    }

    override fun initView(view: View) {
        val categoryLeftAdapter = CategoryLeftAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = categoryLeftAdapter
        }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
              val layoutManager= recyclerView.layoutManager as LinearLayoutManager
               Log.e("TAG", "${layoutManager.findLastVisibleItemPosition()}")
            }
        })
        val categoryRightAdapter = CategoryRightAdapter(this)
        viewPager2.apply {
            orientation = ViewPager2.ORIENTATION_VERTICAL
            adapter = categoryRightAdapter
        }
        viewPager2.isUserInputEnabled=false
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                categoryLeftAdapter.changeSelectPosition(position)
            }
        })
        categoryLeftAdapter.setOnItemClickListener {
            viewPager2.setCurrentItem(it, false)
        }
        categoryViewModel.viewPagerPosition.observe(this, Observer {
            viewPager2.setCurrentItem(it, false)
            recyclerView.scrollToPosition(it)
        })
        etSearch.setOnClickListener {
            toSearchActivity()
        }
        tvSearch.setOnClickListener {
            toSearchActivity()
        }
    }

    /**
     * 搜索
     * 添加统计 跳转等等
     */
    private fun toSearchActivity() {
        startActivity(Intent(requireContext(), SearchActivity::class.java))
    }


}