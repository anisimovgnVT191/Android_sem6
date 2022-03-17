package com.example.android.catsapp.uilayer.catslistfeature.delegateadapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class DelegateAdapterItemCallback : DiffUtil.ItemCallback<DelegateAdapterItem>() {

    override fun areItemsTheSame(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem
    ) = oldItem::class == newItem::class && oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem
    ) = oldItem.content == newItem.content

    override fun getChangePayload(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem
    ): Any = oldItem.payload(newItem)
}