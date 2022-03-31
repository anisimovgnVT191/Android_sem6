package com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource.room

import android.content.Context
import androidx.room.Room
import com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource.CatsLocalDataSource
import com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource.datamodels.FullBreedInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RoomLocalDataSource(
    context: Context,
    private val dispatcher: CoroutineDispatcher
) : CatsLocalDataSource {
    private val database = Room.databaseBuilder(
        context, CatsDatabase::class.java, "cats_database"
    ).build()

    override suspend fun addBreedToDb(breedDetails: FullBreedInfo) {
        withContext(dispatcher) {
            database.breedDao().insertBreed(FullBreedInfo.mapToBreedEntity(breedDetails))
        }

    }

    override suspend fun removeBreedFromDb(breedId: String) {
        withContext(dispatcher) {
            database.breedDao().deleteById(breedId)
        }
    }

    override suspend fun getAllBreedsFromDb(): List<FullBreedInfo> = withContext(dispatcher) {
        database.breedDao().getAllBreeds().map { FullBreedInfo.fromBreedEntity(it) }
    }

    override suspend fun isPresent(breedId: String): Boolean = withContext(dispatcher) {
        database.breedDao().isPresent(breedId)
    }

    override suspend fun getBreedById(breedId: String): FullBreedInfo = withContext(dispatcher) {
        database.breedDao().getBreedById(breedId).let { FullBreedInfo.fromBreedEntity(it) }
    }

}