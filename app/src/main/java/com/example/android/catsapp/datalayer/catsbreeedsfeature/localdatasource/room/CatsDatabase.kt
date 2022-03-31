package com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource.room.dao.BreedDao
import com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource.room.entities.BreedEntity
import com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource.room.entities.CharacteristicsEntity
import com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource.room.entities.ImageEntity

@Database(entities = [BreedEntity::class, CharacteristicsEntity::class, ImageEntity::class], version = 1)
@TypeConverters(ImagesUrlConverter::class)
abstract class CatsDatabase: RoomDatabase() {
    abstract fun breedDao(): BreedDao
}