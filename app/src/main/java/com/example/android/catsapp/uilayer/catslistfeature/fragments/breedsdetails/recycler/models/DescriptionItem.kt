package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.models

import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem

data class DescriptionItem(
    val breedId: String,
    val breedName: String,
    val breedDescription: String,
    val breedTemperament: String,
    val isFavorite: Boolean = false
) : DelegateAdapterItem {

    override val id: Any
        get() = breedId

    override val content: Any
        get() = DescriptionContent(breedName, breedDescription, breedTemperament, isFavorite)

    override fun payload(other: Any): DelegateAdapterItem.Payloadable {
        if (other is DescriptionItem) {
            if (isFavorite != other.isFavorite) {
                return ChangePayload.FavoriteChanged(other.isFavorite)
            }
        }
        return DelegateAdapterItem.Payloadable.None
    }
    data class DescriptionContent(
        val name: String,
        val description: String,
        val temperament: String,
        val isFavorite: Boolean
    )

    sealed class ChangePayload(): DelegateAdapterItem.Payloadable {
        class FavoriteChanged(val isFavorite: Boolean): ChangePayload()
    }
}