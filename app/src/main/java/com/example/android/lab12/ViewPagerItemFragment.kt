package com.example.android.lab12

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.lab12.databinding.FragmentViewPagerBinding
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
        binding.name.text = fragmentName
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