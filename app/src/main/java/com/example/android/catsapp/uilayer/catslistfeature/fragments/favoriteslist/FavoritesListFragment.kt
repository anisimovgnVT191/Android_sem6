package com.example.android.catsapp.uilayer.catslistfeature.fragments.favoriteslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.catsapp.R
import com.example.android.catsapp.databinding.FragmentFavoritesListBinding
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.CompositeAdapter
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.adapters.ErrorAdapter
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.adapters.LoadingAdapter
import com.example.android.catsapp.uilayer.catslistfeature.fragments.favoriteslist.recycler.adapter.FavoriteBreedAdapter
import com.example.android.catsapp.uilayer.catslistfeature.viewmodel.BreedsViewModel


class FavoritesListFragment : Fragment() {
    private var _binding: FragmentFavoritesListBinding? = null
    private val binding: FragmentFavoritesListBinding
        get() = _binding!!

    private val viewModel: BreedsViewModel by activityViewModels()

    private val compositeAdapter = CompositeAdapter.build {
        add(LoadingAdapter())
        add(FavoriteBreedAdapter(
            favoriteClickListener = {
                viewModel.removeFromFavorites(it.breedId)
            },
            holderClickListener = {
                viewModel.fetchBreedImagesByCount(5, it.breedId)
                findNavController().navigate(R.id.action_favorites_to_description)
            }
        ))
        add(ErrorAdapter(
            reloadButtonListener = {}
        ))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesListBinding.inflate(inflater)
        binding.favoriteListRecycler.apply {
            addItemDecoration(
                DividerItemDecoration(
                    this@FavoritesListFragment.context,
                    LinearLayoutManager.VERTICAL
                )
            )
            adapter = compositeAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.favoritesListState.observe(this) { state ->
            compositeAdapter.submitList(state.listFavorites)
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.fetchFavorites()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}