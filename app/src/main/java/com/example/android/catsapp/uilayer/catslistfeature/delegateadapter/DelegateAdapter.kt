package com.example.android.catsapp.uilayer.catslistfeature.delegateadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class DelegateAdapter<M, in VH : RecyclerView.ViewHolder>(val modelClass: Class<out M>) {
    abstract fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun bindViewHolder(
        model: M,
        viewHolder: VH,
        payloads: List<DelegateAdapterItem.Payloadable>
    )

    open fun onViewRecycled(viewHolder: VH) = Unit
}