package com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource.room.entities

import androidx.room.*
import com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource.room.ImagesUrlConverter

@Entity(tableName = "Breed")
data class BreedEntity(
    @PrimaryKey val breedId: String,
    val name: String,
    val temperament: String,
    val description: String,
    val wikiUrl: String,
    val mainImageUrl: String,
    @Embedded val characteristics: CharacteristicsEntity,
    val imagesUrls: List<String>
)
