package com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Image")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val breedId: String,
    val imageUrl: String
)
