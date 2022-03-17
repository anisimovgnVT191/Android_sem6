package com.example.android.catsapp.uilayer.catslistfeature.delegateadapter

interface DelegateAdapterItem {
    val id: Any
    val content: Any
    fun payload(other: Any): Payloadable = Payloadable.None

    interface Payloadable {
        object None : Payloadable
    }
}