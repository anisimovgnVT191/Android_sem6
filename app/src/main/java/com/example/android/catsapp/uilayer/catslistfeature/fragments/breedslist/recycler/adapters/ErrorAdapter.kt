package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.catsapp.R
import com.example.android.catsapp.domainlayer.NoInternetConnectionException
import com.example.android.catsapp.domainlayer.SearchReturnedZeroItemsException
import com.example.android.catsapp.domainlayer.ZeroFavoriteCatsException
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapter
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.models.ErrorItem
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class ErrorAdapter(
    val reloadButtonListener: View.OnClickListener
) :
    DelegateAdapter<ErrorItem, ErrorAdapter.ErrorViewHolder>(ErrorItem::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_breed_error, parent, false)

        return ErrorViewHolder(view)
    }

    override fun bindViewHolder(
        model: ErrorItem,
        viewHolder: ErrorViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model, reloadButtonListener)
    }

    inner class ErrorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val reloadButton: MaterialButton = itemView.findViewById(R.id.update_button)
        private val errorText: MaterialTextView = itemView.findViewById(R.id.error_text)

        fun bind(item: ErrorItem, listener: View.OnClickListener) {

            when(item.exception) {
                is NoInternetConnectionException -> {
                    errorText.text = itemView.context.getString(R.string.no_internet_exception_text)
                    reloadButton.visibility = View.VISIBLE
                    reloadButton.setOnClickListener(listener)
                }
                is SearchReturnedZeroItemsException -> {
                    errorText.text = itemView.context.getString(R.string.search_exception)
                    reloadButton.visibility = View.GONE
                    reloadButton.setOnClickListener(null)
                }
                is ZeroFavoriteCatsException -> {
                    errorText.text = itemView.context.getString(R.string.zero_favorite_cats_exception)
                    reloadButton.visibility = View.GONE
                    reloadButton.setOnClickListener(null)
                }
            }
        }
    }

}