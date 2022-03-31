package com.example.android.catsapp.uilayer.catslistfeature.uistate

import com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource.datamodels.FullBreedInfo
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem

data class FavoriteListUiState(
    val listFavorites: List<DelegateAdapterItem>
)