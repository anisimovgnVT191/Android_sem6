package com.example.android.catsapp.uilayer.catslistfeature.fragments.favoriteslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.android.catsapp.R
import com.example.android.catsapp.databinding.FragmentFavoritesListBinding


class FavoritesListFragment : Fragment() {
    private var _binding: FragmentFavoritesListBinding? = null
    private val binding: FragmentFavoritesListBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesListBinding.inflate(inflater)

        binding.goTo.setOnClickListener {
            findNavController().navigate(R.id.action_favorites_to_description)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}