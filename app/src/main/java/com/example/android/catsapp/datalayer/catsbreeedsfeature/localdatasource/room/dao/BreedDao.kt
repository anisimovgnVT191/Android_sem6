package com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource.room.entities.BreedEntity

@Dao
interface BreedDao {

    @Query("SELECT * FROM Breed")
    fun getAllBreeds(): List<BreedEntity>

    @Insert
    fun insertBreed(breed: BreedEntity)

    @Delete
    fun delete(breed: BreedEntity)
}