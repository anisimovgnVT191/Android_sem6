package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.adapters

import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.catsapp.R
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapter
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.models.HeaderItem
import com.google.android.material.textfield.TextInputEditText

class HeaderAdapter(
    val textWatcher: (CharSequence?, Int, Int, Int) -> Unit
) :
    DelegateAdapter<HeaderItem, HeaderAdapter.HeaderViewHolder>(HeaderItem::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_breed_header, parent, false)

        return HeaderViewHolder(view)
    }

    override fun bindViewHolder(
        model: HeaderItem,
        viewHolder: HeaderViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(textWatcher)
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val searchField: TextInputEditText = itemView.findViewById(R.id.search_field)

        fun bind(textWatcher: (CharSequence?, Int, Int, Int) -> Unit) {
            searchField.doOnTextChanged(textWatcher)
        }
    }
}