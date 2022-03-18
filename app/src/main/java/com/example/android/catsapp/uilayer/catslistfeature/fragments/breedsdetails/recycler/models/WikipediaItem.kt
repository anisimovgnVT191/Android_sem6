package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.models

import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem

class WikipediaItem(
    val url: String
): DelegateAdapterItem {

    override val id: Any
        get() = url

    override val content: Any
        get() = url
}