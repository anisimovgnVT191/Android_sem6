package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.models

import com.example.android.catsapp.uilayer.catslistfeature.datamodels.Breed
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem

class BreedItem(
    val breed: Breed
): DelegateAdapterItem {
    override val id: Any
        get() = breed.breedId
    override val content: Any
        get() = breed

    override fun payload(other: Any): DelegateAdapterItem.Payloadable {
        return super.payload(other)
    }
}