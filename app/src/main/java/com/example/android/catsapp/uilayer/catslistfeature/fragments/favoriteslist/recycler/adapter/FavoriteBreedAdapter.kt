package com.example.android.catsapp.uilayer.catslistfeature.fragments.favoriteslist.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.android.catsapp.R
import com.example.android.catsapp.databinding.FavoriteListRecyclerItemBinding
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.ClickableViewHolder
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapter
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.favoriteslist.recycler.model.FavoriteBreedItem

class FavoriteBreedAdapter(
    private val favoriteClickListener: (FavoriteBreedItem) -> Unit,
    private val holderClickListener: (FavoriteBreedItem) -> Unit
) :
    DelegateAdapter<FavoriteBreedItem, FavoriteBreedAdapter.FavoriteBreedViewHolder>(
        FavoriteBreedItem::class.java
    ) {
    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = FavoriteListRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return FavoriteBreedViewHolder(binding, favoriteClickListener, holderClickListener)
    }

    override fun bindViewHolder(
        model: FavoriteBreedItem,
        viewHolder: FavoriteBreedViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    class FavoriteBreedViewHolder(
        private val binding: FavoriteListRecyclerItemBinding,
        private val favoriteClickListener: (FavoriteBreedItem) -> Unit,
        private val holderClickListener: (FavoriteBreedItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root), ClickableViewHolder {
        private val circularProgress = CircularProgressDrawable(itemView.context).apply {
            strokeWidth = DEFAULT_STROKE_WIDTH
            centerRadius = DEFAULT_CENTER_RADIUS
        }

        fun bind(item: FavoriteBreedItem) {
            with(binding) {
                breedName.text = item.name
                loadImage(item.imageUrl, mainImage)
                bindFavorite(item.isSelected)
            }
        }

        private fun bindFavorite(isFavorite: Boolean) {
            if (isFavorite) {
                binding.favoriteButton.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                binding.favoriteButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }

        private fun loadImage(url: String, to: AppCompatImageView) {
            circularProgress.start()

            Glide.with(to.context)
                .load(url)
                .placeholder(circularProgress)
                .centerCrop()
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(to)
        }

        override fun setListener(getItemAt: (Int) -> DelegateAdapterItem) {
            binding.favoriteButton.setOnClickListener {
                favoriteClickListener(getItemAt(adapterPosition) as FavoriteBreedItem)
            }

            binding.favoriteHolder.setOnClickListener {
                holderClickListener(getItemAt(adapterPosition) as FavoriteBreedItem)
            }
        }

        companion object {
            private const val DEFAULT_STROKE_WIDTH = 5F
            private const val DEFAULT_CENTER_RADIUS = 30F
        }
    }
}