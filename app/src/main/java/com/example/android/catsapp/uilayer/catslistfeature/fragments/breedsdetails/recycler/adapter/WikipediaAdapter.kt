package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.catsapp.R
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapter
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.models.WikipediaItem
import com.google.android.material.button.MaterialButton

class WikipediaAdapter :
    DelegateAdapter<WikipediaItem, WikipediaAdapter.WikipediaViewHolder>(WikipediaItem::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_details_wikipedia, parent, false)

        return WikipediaViewHolder(view)
    }

    override fun bindViewHolder(
        model: WikipediaItem,
        viewHolder: WikipediaViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    inner class WikipediaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val openButton = itemView.findViewById<MaterialButton>(R.id.open_wiki_button)
        private var wikiUrl: String? = null

        fun bind(item: WikipediaItem) {
            wikiUrl = item.url
            openButton.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            wikiUrl?.let {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                p0!!.context.startActivity(intent)
            }
        }


    }
}