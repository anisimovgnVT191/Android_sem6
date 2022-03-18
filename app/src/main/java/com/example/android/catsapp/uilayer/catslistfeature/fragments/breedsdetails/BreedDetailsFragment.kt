package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.catsapp.R
import com.example.android.catsapp.databinding.FragmentBreedDetailsBinding
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.CompositeAdapter
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.adapter.CharacteristicAdapter
import com.example.android.catsapp.uilayer.catslistfeature.viewmodel.BreedsViewModel

class BreedDetailsFragment : Fragment(R.layout.fragment_breed_details) {
    private var _binding: FragmentBreedDetailsBinding? = null
    private val binding: FragmentBreedDetailsBinding
        get() = _binding!!

    private val viewModel: BreedsViewModel by activityViewModels()

    private val compositeAdapter = CompositeAdapter.build {
        add(CharacteristicAdapter())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreedDetailsBinding.inflate(inflater)

        binding.detailsRecycler.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = compositeAdapter
        }

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.breedDetailsState.observe(this) { state ->
            (binding.detailsRecycler.adapter as CompositeAdapter).submitList(state.list)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}