package com.example.android.catsapp.uilayer.catslistfeature.uistate

import com.example.android.catsapp.datalayer.catsbreeedsfeature.datamodels.getimages.ImagesItem

data class BreedDetailsUiState(
    val list: List<ImagesItem> = emptyList()
) {
}