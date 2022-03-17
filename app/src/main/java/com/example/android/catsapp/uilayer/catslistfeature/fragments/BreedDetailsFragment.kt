package com.example.android.catsapp.uilayer.catslistfeature.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.android.catsapp.R
import com.example.android.catsapp.uilayer.catslistfeature.viewmodel.BreedsViewModel

class BreedDetailsFragment : Fragment(R.layout.fragment_breed_details) {
    private val viewModel: BreedsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.breedDetailsState.observe(this) { state ->
            Log.e("onViewCreated", state.toString())
        }
    }
}