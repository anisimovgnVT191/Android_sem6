package com.example.android.catsapp.uilayer.catslistfeature.uistate

import com.example.android.catsapp.datalayer.catsbreeedsfeature.datamodels.getimages.ImagesItem
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem

data class BreedDetailsUiState(
    val list: List<DelegateAdapterItem> = emptyList()
) {
}