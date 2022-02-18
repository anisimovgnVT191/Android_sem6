package com.example.android.lab12

import android.graphics.drawable.Drawable
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
            tab.icon = iconFromPosition(position)
        }.attach()
    }

    private fun iconFromPosition(position: Int) =
        with(resources){
            when(position){
                0 -> getDrawable(R.drawable.ic_baseline_sports_volleyball_24)
                1 -> getDrawable(R.drawable.ic_baseline_sports_tennis_24)
                2 -> getDrawable(R.drawable.ic_baseline_sports_soccer_24)
                3 -> getDrawable(R.drawable.ic_baseline_sports_rugby_24)
                4 -> getDrawable(R.drawable.ic_baseline_sports_motorsports_24)
                5 -> getDrawable(R.drawable.ic_baseline_sports_mma_24)
                6 -> getDrawable(R.drawable.ic_baseline_sports_kabaddi_24)
                7 -> getDrawable(R.drawable.ic_baseline_sports_hockey_24)
                8 -> getDrawable(R.drawable.ic_baseline_sports_handball_24)
                9 -> getDrawable(R.drawable.ic_baseline_sports_golf_24)
                else -> getDrawable(R.drawable.ic_baseline_disabled_by_default_24)
            }
        }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}