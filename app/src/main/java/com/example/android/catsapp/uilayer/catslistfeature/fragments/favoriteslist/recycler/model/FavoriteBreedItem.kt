package com.example.android.catsapp.uilayer.catslistfeature.fragments.favoriteslist.recycler.model

import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem

class FavoriteBreedItem(
    val breedId: String,
    val name: String,
    val imageUrl: String,
    val isSelected: Boolean
) : DelegateAdapterItem {
    override val id = breedId
    override val content = FavoriteBreedContent(imageUrl, isSelected, name)

    data class FavoriteBreedContent(val imageUrl: String, val isSelected: Boolean, val name: String)
}