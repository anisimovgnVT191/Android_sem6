package com.example.android.lab12

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.ConcatAdapter
import com.example.android.lab12.databinding.FragmentViewPagerItemBinding

class ViewPagerItemFragment : Fragment() {
    private var fragmentName: String? = null
    private var _binding: FragmentViewPagerItemBinding? = null
    private val binding: FragmentViewPagerItemBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fragmentName = it.getString(FRAGMENT_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewPagerItemBinding.inflate(layoutInflater)
        binding.postsRecycler.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)//.apply {
//                gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
//            }
            adapter = ConcatAdapter(HeaderAdapter(fragmentName?:""), PostsAdapter(sampleData))
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val FRAGMENT_NAME= "FRAGMENT_NAME"
        @JvmStatic
        fun newInstance(param1: String) =
            ViewPagerItemFragment().apply {
                arguments = Bundle().apply {
                    putString(FRAGMENT_NAME, param1)
                }
            }
    }

}