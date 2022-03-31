package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.android.catsapp.R
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapter
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.models.SingleImageItem
import com.google.android.material.imageview.ShapeableImageView

class SingleImageAdapter :
    DelegateAdapter<SingleImageItem, SingleImageAdapter.SingleImageViewHolder>(SingleImageItem::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.images_recycler_item, parent, false)

        return SingleImageViewHolder(view)
    }

    override fun bindViewHolder(
        model: SingleImageItem,
        viewHolder: SingleImageViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    class SingleImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageHolder = itemView.findViewById<ShapeableImageView>(R.id.cat_image)

        fun bind(item: SingleImageItem) {
            loadImage(item.imageUrl)
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
                .circleCrop()
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(imageHolder)
        }
    }
}