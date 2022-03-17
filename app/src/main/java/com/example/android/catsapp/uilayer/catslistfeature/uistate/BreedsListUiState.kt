package com.example.android.catsapp.uilayer.catslistfeature.uistate

import com.example.android.catsapp.uilayer.catslistfeature.datamodels.Breed

data class BreedsListUiState(
    val isErrorOccurred: Boolean = false,
    val error: Exception? = null,
    val breedsList: List<Breed> = emptyList()
)
