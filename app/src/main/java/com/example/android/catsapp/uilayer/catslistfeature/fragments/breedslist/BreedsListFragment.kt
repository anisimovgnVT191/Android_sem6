package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.catsapp.R
import com.example.android.catsapp.databinding.FragmentBreedsListBinding
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.CompositeAdapter
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.BreedDetailsFragment
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.adapters.BreedAdapter
import com.example.android.catsapp.uilayer.catslistfeature.viewmodel.BreedsViewModel

class BreedsListFragment : Fragment(),
    HasDefaultViewModelProviderFactory {
    private var _binding: FragmentBreedsListBinding? = null
    private val binding: FragmentBreedsListBinding
        get() = _binding!!

    private val viewModel: BreedsViewModel by activityViewModels()

    private val compositeAdapter = CompositeAdapter.build {
        add(BreedAdapter())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreedsListBinding.inflate(inflater)

        if (savedInstanceState == null) {
            viewModel.fetchBreeds()
        }

       binding.breedRecycler.apply {
           layoutManager = GridLayoutManager(this.context, 2)
           adapter = compositeAdapter
       }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.breedsListUiState.observe(this) { state ->
            if (!state.isErrorOccurred) {
                (binding.breedRecycler.adapter as CompositeAdapter).submitList(state.breedsList)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}