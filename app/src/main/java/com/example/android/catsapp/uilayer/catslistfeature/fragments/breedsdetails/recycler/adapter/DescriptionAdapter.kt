package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.catsapp.R
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapter
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.models.DescriptionItem
import com.google.android.material.textview.MaterialTextView

class DescriptionAdapter :
    DelegateAdapter<DescriptionItem, DescriptionAdapter.DescriptionViewHolder>(DescriptionItem::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_details_description, parent, false)

        return DescriptionViewHolder(view)
    }

    override fun bindViewHolder(
        model: DescriptionItem,
        viewHolder: DescriptionViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    inner class DescriptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<MaterialTextView>(R.id.title_breed)
        private val description = itemView.findViewById<MaterialTextView>(R.id.breed_description)
        private val temperament =
            itemView.findViewById<MaterialTextView>(R.id.breed_temperament_description)

        fun bind(item: DescriptionItem) {
            title.text = item.breedName
            description.text = item.breedDescription
            temperament.text = item.breedTemperament
        }
    }
}