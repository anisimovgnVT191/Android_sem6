package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.catsapp.R
import com.example.android.catsapp.databinding.RecyclerDetailsDescriptionBinding
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.ClickableViewHolder
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapter
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.models.DescriptionItem
import com.google.android.material.textview.MaterialTextView

class DescriptionAdapter(
    private val favoriteListener: (DescriptionItem) -> Unit
) :
    DelegateAdapter<DescriptionItem, DescriptionAdapter.DescriptionViewHolder>(DescriptionItem::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = RecyclerDetailsDescriptionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return DescriptionViewHolder(binding, favoriteListener)
    }

    override fun bindViewHolder(
        model: DescriptionItem,
        viewHolder: DescriptionViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    class DescriptionViewHolder(
        private val binding: RecyclerDetailsDescriptionBinding,
        private val favoriteListener: (DescriptionItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root), ClickableViewHolder {

        fun bind(item: DescriptionItem) {
            with (binding) {
                bindFavorite(item.isFavorite)
                titleBreed.text = item.breedName
                breedDescription.text = item.breedDescription
                breedTemperamentDescription.text = item.breedTemperament
            }
        }

        fun bindFavorite(isFavorite: Boolean) {
            if (isFavorite) {
                binding.favoriteSwitch.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                binding.favoriteSwitch.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }

        override fun setListener(getItemAt: (Int) -> DelegateAdapterItem) {
            binding.favoriteSwitch.setOnClickListener {
                favoriteListener(getItemAt(adapterPosition) as DescriptionItem)
            }
        }
    }
}