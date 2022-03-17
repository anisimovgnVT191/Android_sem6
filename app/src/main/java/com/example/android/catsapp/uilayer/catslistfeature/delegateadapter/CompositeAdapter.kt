package com.example.android.catsapp.uilayer.catslistfeature.delegateadapter

import android.util.Log
import android.util.SparseArray
import android.view.ViewGroup
import androidx.core.util.forEach
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CompositeAdapter(
    private val delegates: SparseArray<DelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>>
) : ListAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>(DelegateAdapterItemCallback()) {

    override fun getItemViewType(position: Int): Int {
        delegates.forEach { key, delegate ->
            if (delegate.modelClass == getItem(position).javaClass) {
                return key
            }
        }
        throw NullPointerException("No viewType found for position $position")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.e("onCreateViewHolder", "VW = $viewType")
        return delegates[viewType].createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        onBindViewHolder(holder, position, mutableListOf())

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val delegateAdapter = delegates[getItemViewType(position)]

        delegateAdapter?.let {
            val delegatePayloads = payloads.map { it as DelegateAdapterItem.Payloadable }
            delegateAdapter.bindViewHolder(getItem(position), holder, delegatePayloads)
        } ?: throw NullPointerException("No adapter found for position $position")
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        delegates[holder.itemViewType].onViewRecycled(holder)
        super.onViewRecycled(holder)
    }

    companion object {
        fun build(action: Builder.() -> Builder): CompositeAdapter = Builder().action().build()
    }

    class Builder {
        private var count: Int = 0
        private var delegates: SparseArray<DelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>> =
            SparseArray()

        fun add(delegateAdapter: DelegateAdapter<out DelegateAdapterItem, *>): Builder {
            delegates.put(
                count++,
                delegateAdapter as DelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>
            )
            return this
        }

        fun build(): CompositeAdapter {
            require(count != 0) { "At least one adapter required" }
            return CompositeAdapter(delegates)
        }
    }
}