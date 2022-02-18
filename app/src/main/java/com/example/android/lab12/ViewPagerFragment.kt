package com.example.android.lab12

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.android.lab12.databinding.FragmentViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerFragment : Fragment() {
    private var _binding: FragmentViewPagerBinding? = null
    private val binding: FragmentViewPagerBinding
        get() = _binding!!
    private lateinit var adapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewPagerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ViewPagerAdapter(this)
        viewPager = binding.viewpager
        viewPager.adapter = adapter
        val tabLayout = binding.tabsLayout
        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = "Item $position"
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}