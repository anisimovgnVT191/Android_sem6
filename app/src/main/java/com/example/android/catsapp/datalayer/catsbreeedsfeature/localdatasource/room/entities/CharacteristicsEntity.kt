package com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Characteristics")
data class CharacteristicsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val affectionLevel: Int,
    val adaptability: Int,
    val childFriendly: Int,
    val dogFriendly: Int,
    val energyLevel: Int,
    val grooming: Int,
    val healthIssues: Int,
    val intelligence: Int,
    val sheddingLevel: Int,
    val socialNeeds: Int,
    val strangerFriendly: Int,
    val vocalisation: Int
)
