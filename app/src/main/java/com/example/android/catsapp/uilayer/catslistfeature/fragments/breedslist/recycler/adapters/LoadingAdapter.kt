package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.catsapp.R
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapter
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.models.LoadingItem

class LoadingAdapter :
    DelegateAdapter<LoadingItem, LoadingAdapter.LoadingViewHolder>(LoadingItem::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_breed_loading, parent, false)

        return LoadingViewHolder(view)
    }

    override fun bindViewHolder(
        model: LoadingItem,
        viewHolder: LoadingViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}