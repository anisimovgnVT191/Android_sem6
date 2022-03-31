package com.example.android.catsapp.datalayer.catsbreeedsfeature.remotedatasource

import com.example.android.catsapp.datalayer.catsbreeedsfeature.remotedatasource.datamodels.getbreeds.Breeds
import com.example.android.catsapp.datalayer.catsbreeedsfeature.remotedatasource.datamodels.getimages.Images
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsApi {

    @GET("breeds")
    suspend fun getBreeds(
        @Query("attach_breed") attachBreed: Int? = null,
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null

    ): Response<Breeds>

    @GET("images/search")
    suspend fun getCountImagesOfBreed(
        @Query("size") size: String? = null,
        @Query("mime_types") mimeTypes: Array<String>? = null,
        @Query("order") order: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("page") page: Int? = null,
        @Query("category_ids") categoryIds: Array<Int>? = null,
        @Query("format") format: String? = null,
        @Query("breed_id") breedId: String? = null
    ): Response<Images>
}