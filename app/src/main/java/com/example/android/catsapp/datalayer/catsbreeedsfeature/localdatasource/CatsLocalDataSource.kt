package com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource

import com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource.datamodels.FullBreedInfo

interface CatsLocalDataSource {
    suspend fun addBreedToDb(breedDetails: FullBreedInfo)

    suspend fun removeBreedFromDb(breedDetails: FullBreedInfo)

    suspend fun getAllBreedsFromDb(): List<FullBreedInfo>
}