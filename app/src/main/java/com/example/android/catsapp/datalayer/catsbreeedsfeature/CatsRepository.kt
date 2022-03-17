package com.example.android.catsapp.datalayer.catsbreeedsfeature

import com.example.android.catsapp.datalayer.catsbreeedsfeature.datamodels.CatsRemoteDataSource
import com.example.android.catsapp.datalayer.catsbreeedsfeature.datamodels.getbreeds.Breeds
import com.example.android.catsapp.datalayer.catsbreeedsfeature.datamodels.getimages.Images
import com.example.android.catsapp.domainlayer.Either

class CatsRepository(
    private val catsDataSource: CatsRemoteDataSource
) {

    suspend fun getBreeds(): Either<Exception, Breeds> {
        val response = try {
            catsDataSource.getBreeds()
        } catch (e: Exception) {
            return Either.Left(e)
        }

        return if (response.isSuccessful && response.body() != null) {
            Either.Right(response.body()!!)
        } else {
            Either.Left(Exception("Error message = ${response.message()}"))
        }
    }

    suspend fun getBreedsImageById(
        count: Int = 1,
        breedsId: String
    ): Either<Exception, Images> {
        val response = try {
            catsDataSource.getBreedsImageById(count, breedsId)
        } catch (e: Exception) {
            return Either.Left(e)
        }

        return if (response.isSuccessful && response.body() != null) {
            Either.Right(response.body()!!)
        } else {
            Either.Left(Exception("Error message = ${response.message()}"))
        }
    }
}