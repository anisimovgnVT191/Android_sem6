package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.models

import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem

class CharacteristicItem(
    val characteristics: Characteristics,
    val value: Int
): DelegateAdapterItem {
    override val id: Any
        get() = characteristics
    override val content: Any
        get() = value
}