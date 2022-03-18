package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.models

import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem

class ImagesItem(
    val imagesUrlList: List<String>
) : DelegateAdapterItem {
    override val id: Any
        get() = 1
    override val content: Any
        get() = imagesUrlList
}