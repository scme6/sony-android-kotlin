package com.sony.store.myapplication.ui.fragment

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.sony.store.myapplication.R
import com.sony.store.myapplication.adapter.MainListAdapter
import com.sony.store.myapplication.base.BaseFragment
import com.sony.store.myapplication.ui.activity.SearchActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.include_search.*

/**
 * @Description 一级 首页
 * @author: qiang
 * @date: 2021-06-27
 */
class HomeFragment : BaseFragment() {
    override fun setContentView() = R.layout.fragment_home

    override fun initView(view: View) {
        val listAdapter = MainListAdapter(this)
        listAdapter.onTabsLoaded()
        main_recycler_view.layoutManager = LinearLayoutManager(requireContext())
        main_recycler_view.adapter = listAdapter

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