package com.example.android.catsapp.uilayer.catslistfeature.uistate

import com.example.android.catsapp.uilayer.catslistfeature.datamodels.Breed
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem

data class BreedsListUiState(
    val isErrorOccurred: Boolean = false,
    val error: Exception? = null,
    val breedsList: List<DelegateAdapterItem> = emptyList()
)
