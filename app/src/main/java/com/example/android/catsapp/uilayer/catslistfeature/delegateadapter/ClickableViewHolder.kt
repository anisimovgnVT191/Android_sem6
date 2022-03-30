package com.example.android.catsapp.uilayer.catslistfeature.delegateadapter

interface ClickableViewHolder {
    fun setListener(getItemAt: (Int) -> DelegateAdapterItem)
}