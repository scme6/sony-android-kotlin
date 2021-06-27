package com.sony.store.myapplication.adapter
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sony.store.myapplication.ui.FeedsListFragment

class FeedsFragmentAdapter(fm: Fragment) : FragmentStateAdapter(fm) {
    override fun createFragment(position: Int): Fragment {
        return FeedsListFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }

}