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

    @Query("DELETE FROM Breed WHERE breedId = :breedId")
    fun deleteById(breedId: String)

    @Query("SELECT EXISTS(SELECT * FROM Breed WHERE breedId = :breedId)")
    fun isPresent(breedId: String): Boolean

    @Query("SELECT * FROM Breed WHERE breedId = :breedId")
    fun getBreedById(breedId: String): BreedEntity
}