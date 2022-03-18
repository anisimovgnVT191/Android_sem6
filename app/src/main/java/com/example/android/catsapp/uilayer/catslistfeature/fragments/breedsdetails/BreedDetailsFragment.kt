package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.catsapp.R
import com.example.android.catsapp.databinding.FragmentBreedDetailsBinding
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.CompositeAdapter
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.adapter.CharacteristicAdapter
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.adapter.DescriptionAdapter
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.adapter.ImagesAdapter
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.adapters.ErrorAdapter
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.adapters.LoadingAdapter
import com.example.android.catsapp.uilayer.catslistfeature.viewmodel.BreedsViewModel

class BreedDetailsFragment : Fragment(R.layout.fragment_breed_details) {
    private var _binding: FragmentBreedDetailsBinding? = null
    private val binding: FragmentBreedDetailsBinding
        get() = _binding!!

    private val viewModel: BreedsViewModel by activityViewModels()

    private lateinit var breedId: String

    private val compositeAdapter = CompositeAdapter.build {
        add(CharacteristicAdapter())
        add(DescriptionAdapter())
        add(ImagesAdapter())
        add(LoadingAdapter())
        add(ErrorAdapter(reloadButtonListener = {
            viewModel.fetchBreedImagesByCount(5, breedId)
        }))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        breedId = arguments?.getString(BREED_ID).orEmpty()
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
            addItemDecoration(DividerItemDecoration(this@BreedDetailsFragment.context, LinearLayoutManager.VERTICAL))
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

    companion object {
        private const val BREED_ID = "BREED_IT"

        @JvmStatic
        fun newInstance(breedId: String): BreedDetailsFragment =
            BreedDetailsFragment().apply {
                arguments = bundleOf(BREED_ID to breedId)
            }
    }
}