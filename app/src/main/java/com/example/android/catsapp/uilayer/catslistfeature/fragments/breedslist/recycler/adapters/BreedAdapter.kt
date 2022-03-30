package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.android.catsapp.R
import com.example.android.catsapp.databinding.BreedItemLayoutBinding
import com.example.android.catsapp.datalayer.catsbreeedsfeature.datamodels.getimages.Breed
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.ClickableViewHolder
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapter
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.models.BreedItem
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView

class BreedAdapter(
    private val listener: (BreedItem) -> Unit
) :
    DelegateAdapter<BreedItem, BreedAdapter.BreedViewHolder>(BreedItem::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding =
            BreedItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return BreedViewHolder(binding, listener)
    }

    override fun bindViewHolder(
        model: BreedItem,
        viewHolder: BreedViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    class BreedViewHolder(
        private val binding: BreedItemLayoutBinding,
        private val breedListener: (BreedItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root), ClickableViewHolder {
        private val circularProgress = CircularProgressDrawable(itemView.context).apply {
            strokeWidth = DEFAULT_STROKE_WIDTH
            centerRadius = DEFAULT_CENTER_RADIUS
        }

        fun bind(item: BreedItem) {
            with (binding) {
                breedTemperament.text = item.breed.temperament
                breedName.text = item.breed.name
                loadImage(item.breed.imageUrl, this.catImage)
            }
        }

        private fun loadImage(url: String, to: AppCompatImageView) {
            Glide.with(to.context)
                .load(url)
                .placeholder(circularProgress)
                .centerCrop()
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(to)
        }

        override fun setListener(getItemAt: (Int) -> DelegateAdapterItem) {
            binding.breedHolder.setOnClickListener {
                breedListener(getItemAt(adapterPosition) as BreedItem)
            }
        }

        companion object {
            private const val DEFAULT_STROKE_WIDTH = 5F
            private const val DEFAULT_CENTER_RADIUS = 30F
        }
    }
}