package com.example.android.catsapp.datalayer.catsbreeedsfeature.remotedatasource

import com.example.android.catsapp.datalayer.catsbreeedsfeature.remotedatasource.datamodels.getbreeds.Breeds
import com.example.android.catsapp.datalayer.catsbreeedsfeature.remotedatasource.datamodels.getimages.Images
import retrofit2.Response

interface CatsRemoteDataSource {

    suspend fun getBreeds(): Response<Breeds>

    suspend fun getBreedsImageById(count: Int = 1, breedId: String): Response<Images>
}