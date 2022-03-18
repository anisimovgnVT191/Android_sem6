package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.models

import com.example.android.catsapp.domainlayer.CatsAppExceptions
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem

class ErrorItem(
    val exception: CatsAppExceptions
) : DelegateAdapterItem {
    override val id: Any
        get() = 1
    override val content: Any
        get() = 1

    override fun payload(other: Any): DelegateAdapterItem.Payloadable {
        return super.payload(other)
    }
}