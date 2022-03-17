package com.example.android.catsapp.uilayer.catslistfeature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.catsapp.datalayer.catsbreeedsfeature.CatsRepository

class BreedsDetailsViewModelFactory(
    private val catsRepository: CatsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BreedsViewModel::class.java)) {
            return BreedsViewModel(catsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}