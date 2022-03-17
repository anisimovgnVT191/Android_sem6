package com.example.android.catsapp.uilayer.catslistfeature.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.catsapp.datalayer.catsbreeedsfeature.CatsRepository
import com.example.android.catsapp.domainlayer.Either
import com.example.android.catsapp.uilayer.catslistfeature.datamodels.Breed
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.models.BreedItem
import com.example.android.catsapp.uilayer.catslistfeature.uistate.BreedDetailsUiState
import com.example.android.catsapp.uilayer.catslistfeature.uistate.BreedsListUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BreedsViewModel(
    private val repository: CatsRepository
) : ViewModel() {
    private val _breedsListState = MutableLiveData(BreedsListUiState())
    val breedsListUiState: LiveData<BreedsListUiState>
        get() = _breedsListState

    private val _breedDetailsState = MutableLiveData(BreedDetailsUiState())
    val breedDetailsState: LiveData<BreedDetailsUiState>
        get() = _breedDetailsState

    private var fetchBreedsJob: Job? = null
    fun fetchBreeds() {
        fetchBreedsJob?.cancel()

        fetchBreedsJob = viewModelScope.launch {
            val result = repository.getBreeds()

            if (result is Either.Right) {
                _breedsListState.value = BreedsListUiState(
                    breedsList = result.right.map { BreedItem(Breed.from(it)) }
                )
            }
        }
    }

    private var fetchBreedImagesByCountJob: Job? = null
    fun fetchBreedImagesByCount(count: Int, breedsId: String) {
        fetchBreedImagesByCountJob?.cancel()

        fetchBreedImagesByCountJob = viewModelScope.launch {
            val result = repository.getBreedsImageById(count, breedsId)

            if (result is Either.Right) {
                _breedDetailsState.value = BreedDetailsUiState(
                    list = result.right
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        fetchBreedsJob?.cancel()
    }
}