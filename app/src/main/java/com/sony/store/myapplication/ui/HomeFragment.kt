package com.sony.store.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sony.store.myapplication.R
import com.sony.store.myapplication.adapter.MainListAdapter
import com.sony.store.myapplication.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @Description
 * @author: qiang
 * @date: 2021-06-27
 */
class HomeFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val listAdapter = MainListAdapter(this)
        listAdapter.onTabsLoaded()
        main_recycler_view.layoutManager = LinearLayoutManager(requireContext())
        main_recycler_view.adapter = listAdapter
    }

}