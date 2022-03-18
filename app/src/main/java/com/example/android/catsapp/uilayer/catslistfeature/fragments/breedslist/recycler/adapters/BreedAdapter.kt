package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.adapters

import android.telephony.TelephonyCallback
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.android.catsapp.R
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapter
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.models.BreedItem
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView

class BreedAdapter(
    private val listener: (String) -> Unit
) :
    DelegateAdapter<BreedItem, BreedAdapter.BreedViewHolder>(BreedItem::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.breed_item_layout, parent, false)

        return BreedViewHolder(view)
    }

    override fun bindViewHolder(
        model: BreedItem,
        viewHolder: BreedViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model, listener)
    }

    inner class BreedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageHolder = itemView.findViewById<AppCompatImageView>(R.id.cat_image)
        private val breedName = itemView.findViewById<MaterialTextView>(R.id.breed_name)
        private val breedTemperament =
            itemView.findViewById<MaterialTextView>(R.id.breed_temperament)
        private val breedHolder = itemView.findViewById<MaterialCardView>(R.id.breed_holder)

        fun bind(item: BreedItem, listener: (String) -> Unit) {
            breedTemperament.text = item.breed.temperament
            breedName.text = item.breed.name
            breedHolder.setOnClickListener {
                listener(item.breed.breedId)
            }
            loadImage(item.breed.imageUrl)
        }

        private fun loadImage(url: String) {
            val circularProgress = CircularProgressDrawable(itemView.context).apply {
                strokeWidth = 5F
                centerRadius = 30F
                start()
            }

            Glide.with(itemView.context)
                .load(url)
                .placeholder(circularProgress)
                .centerCrop()
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(imageHolder)
        }
    }
}