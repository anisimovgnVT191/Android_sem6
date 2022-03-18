package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.models

import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem

class DescriptionItem(
    val breedId: String,
    val breedName: String,
    val breedDescription: String,
    val breedTemperament: String
) : DelegateAdapterItem {

    override val id: Any
        get() = breedId

    override val content: Any
        get() = DescriptionContent(breedName, breedDescription, breedTemperament)

    data class DescriptionContent(
        val name: String,
        val description: String,
        val temperament: String
    )
}