package com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource

import com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource.datamodels.FullBreedInfo

interface CatsLocalDataSource {
    suspend fun addBreedToDb(breedDetails: FullBreedInfo)

    suspend fun removeBreedFromDb(breedId: String)

    suspend fun getAllBreedsFromDb(): List<FullBreedInfo>

    suspend fun isPresent(breedId: String): Boolean

    suspend fun getBreedById(breedId: String): FullBreedInfo
}