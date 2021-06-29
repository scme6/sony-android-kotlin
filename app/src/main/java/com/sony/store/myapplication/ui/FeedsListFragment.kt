package com.sony.store.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sony.store.myapplication.adapter.FeedsListAdapter
import com.sony.store.myapplication.base.BaseFragment
import com.stone.persistent.recyclerview.library.ChildRecyclerView

class FeedsListFragment : BaseFragment() {

    override fun setContentView(): Any {
        return ChildRecyclerView(requireContext()).apply {
            layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun initView(view: View) {
        view as RecyclerView
        view.adapter = FeedsListAdapter()
    }

}