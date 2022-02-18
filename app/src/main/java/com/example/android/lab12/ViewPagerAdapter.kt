package com.example.android.lab12

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = ITEM_COUNT

    override fun createFragment(position: Int) = ViewPagerItemFragment.newInstance("Item $position")

    companion object{
        private var ITEM_COUNT: Int = 10
    }

}