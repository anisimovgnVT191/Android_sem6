package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.models

import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem

class HeaderItem(
    val searchText: String
) : DelegateAdapterItem {
    override val id: Any
        get() = 1
    override val content: Any
        get() = 1
}