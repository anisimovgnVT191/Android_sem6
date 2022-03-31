package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.models

import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem

class SingleImageItem(
    val imageUrl: String
) : DelegateAdapterItem {
    override val id = imageUrl
    override val content = imageUrl
}