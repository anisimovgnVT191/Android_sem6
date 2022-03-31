package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.catsapp.R
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.CompositeAdapter
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapter
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.models.ImagesItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.models.SingleImageItem

class ImagesAdapter :
    DelegateAdapter<ImagesItem, ImagesAdapter.ImagesViewHolder>(ImagesItem::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_details_images, parent, false)

        return ImagesViewHolder(view)
    }

    override fun bindViewHolder(
        model: ImagesItem,
        viewHolder: ImagesViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    class ImagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val compositeAdapter = CompositeAdapter.build {
            add(SingleImageAdapter())
        }

        init {
            val imagesRecycler = itemView.findViewById<RecyclerView>(R.id.images_recycler)
            imagesRecycler.adapter = compositeAdapter
        }

        fun bind(item: ImagesItem) {
            compositeAdapter.submitList(
                item.imagesUrlList.map { SingleImageItem(it) }
            )
        }
    }
}