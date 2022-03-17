package com.example.android.catsapp.uilayer.catslistfeature.datamodels

import android.util.Log
import com.example.android.catsapp.datalayer.catsbreeedsfeature.datamodels.getbreeds.BreedsItem

data class Breed(
    val breedId: String,
    val name: String,
    val natural: Boolean,
    val lifeSpan: String,
    val weight: String,
    val country: String,
    val imageUrl: String,
    val temperament: String,
) {
    companion object {
        fun from(breedsItem: BreedsItem) = with(breedsItem) {
            Log.e("from", breedsItem.toString())
            Breed(
                breedId = id,
                name = name,
                natural = natural == 1,
                lifeSpan = life_span,
                weight = weight.metric,
                country = origin,
                imageUrl = image?.url.orEmpty(),
                temperament = temperament
            )
        }
    }
}