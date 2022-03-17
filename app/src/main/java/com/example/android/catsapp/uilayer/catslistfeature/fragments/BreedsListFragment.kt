package com.example.android.catsapp.uilayer.catslistfeature.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import com.example.android.catsapp.R
import com.example.android.catsapp.databinding.FragmentBreedsListBinding
import com.example.android.catsapp.uilayer.catslistfeature.viewmodel.BreedsViewModel

class BreedsListFragment : Fragment(),
    HasDefaultViewModelProviderFactory {
    private var _binding: FragmentBreedsListBinding? = null
    private val binding: FragmentBreedsListBinding
        get() = _binding!!

    private val viewModel: BreedsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreedsListBinding.inflate(inflater)

        if (savedInstanceState == null) {
            viewModel.fetchBreeds()
        }

        binding.button.setOnClickListener {
            viewModel.fetchBreedImagesByCount(5, "abys")
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BreedDetailsFragment()).addToBackStack(null)
                .commit()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.breedsListUiState.observe(this) { state ->
            if (!state.isErrorOccurred) {
                Log.e("onViewCreated", state.breedsList.toString())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}