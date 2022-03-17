package com.example.android.catsapp.datalayer.catsbreeedsfeature.datamodels

import com.example.android.catsapp.datalayer.catsbreeedsfeature.datamodels.getbreeds.Breeds
import com.example.android.catsapp.datalayer.catsbreeedsfeature.datamodels.getimages.Images
import retrofit2.Response

interface CatsRemoteDataSource {

    suspend fun getBreeds(): Response<Breeds>

    suspend fun getBreedsImageById(count: Int = 1, breedId: String): Response<Images>
}