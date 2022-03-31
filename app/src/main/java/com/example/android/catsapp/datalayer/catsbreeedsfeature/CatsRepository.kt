package com.example.android.catsapp.datalayer.catsbreeedsfeature

import android.util.Log
import com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource.CatsLocalDataSource
import com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource.datamodels.FullBreedInfo
import com.example.android.catsapp.datalayer.catsbreeedsfeature.remotedatasource.CatsRemoteDataSource
import com.example.android.catsapp.datalayer.catsbreeedsfeature.remotedatasource.datamodels.getbreeds.Breeds
import com.example.android.catsapp.domainlayer.Either

class CatsRepository(
    private val remoteSource: CatsRemoteDataSource,
    private val localSource: CatsLocalDataSource
) {

    suspend fun getBreeds(): Either<Exception, Breeds> {
        val response = try {
            remoteSource.getBreeds()
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
    ): Either<Exception, FullBreedInfo> {
        val isPresent = localSource.isPresent(breedsId)

        if (isPresent) {
            val result = localSource.getBreedById(breedsId)
            return Either.Right(result)
        }
        val response = try {
            remoteSource.getBreedsImageById(count, breedsId)
        } catch (e: Exception) {
            return Either.Left(e)
        }

        return if (response.isSuccessful && response.body() != null) {
            val result = response.body()
            Log.e("getBreeds", isPresent.toString())
            Either.Right(FullBreedInfo.fromImageItem(result!!, false))
        } else {
            Either.Left(Exception("Error message = ${response.message()}"))
        }
    }

    suspend fun getAllFavorites(): List<FullBreedInfo> = localSource.getAllBreedsFromDb()

    suspend fun addToFavorites(breed: FullBreedInfo) {
        localSource.addBreedToDb(breed)
    }

    suspend fun deleteFromFavorites(breedId: String) {
        localSource.removeBreedFromDb(breedId)
    }
}