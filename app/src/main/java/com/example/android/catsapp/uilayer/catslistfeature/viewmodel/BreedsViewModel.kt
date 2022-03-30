package com.example.android.catsapp.uilayer.catslistfeature.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.catsapp.datalayer.catsbreeedsfeature.CatsRepository
import com.example.android.catsapp.datalayer.catsbreeedsfeature.datamodels.getbreeds.BreedsItem
import com.example.android.catsapp.datalayer.catsbreeedsfeature.datamodels.getimages.ImagesItem
import com.example.android.catsapp.domainlayer.Either
import com.example.android.catsapp.domainlayer.NoInternetConnectionException
import com.example.android.catsapp.domainlayer.SearchReturnedZeroItemsException
import com.example.android.catsapp.uilayer.catslistfeature.datamodels.Breed
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.models.CharacteristicItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.models.Characteristics
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.models.DescriptionItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.models.WikipediaItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.models.BreedItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.models.ErrorItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.models.HeaderItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.models.LoadingItem
import com.example.android.catsapp.uilayer.catslistfeature.uistate.BreedDetailsUiState
import com.example.android.catsapp.uilayer.catslistfeature.uistate.BreedsListUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BreedsViewModel(
    private val repository: CatsRepository
) : ViewModel() {
    private val _breedsListState =
        MutableLiveData(BreedsListUiState(breedsList = listOf(LoadingItem())))
    val breedsListUiState: LiveData<BreedsListUiState>
        get() = _breedsListState

    private val _breedDetailsState = MutableLiveData(BreedDetailsUiState(list = listOf(LoadingItem())))
    val breedDetailsState: LiveData<BreedDetailsUiState>
        get() = _breedDetailsState

    private var searchText: String = ""

    private var breedsList: List<BreedsItem>? = null
    private var fetchBreedsJob: Job? = null
    fun fetchBreeds() {
        fetchBreedsJob?.cancel()

        _breedsListState.value = breedsListUiState.value!!.copy(
            breedsList = listOf(LoadingItem())
        )

        fetchBreedsJob = viewModelScope.launch {
            val result = repository.getBreeds()

            if (result is Either.Right) {
                breedsList = result.right
                _breedsListState.value = BreedsListUiState(
                    breedsList = listOf(HeaderItem(searchText)) + result.right.map { BreedItem(Breed.from(it)) }
                )
            } else {
                _breedsListState.value = BreedsListUiState(
                    breedsList = listOf(ErrorItem(NoInternetConnectionException()))
                )
            }
        }
    }

    private var fetchBreedImagesByCountJob: Job? = null
    fun fetchBreedImagesByCount(count: Int, breedsId: String) {
        fetchBreedImagesByCountJob?.cancel()

        _breedDetailsState.value = breedDetailsState.value!!.copy(
            list = listOf(LoadingItem())
        )
        fetchBreedImagesByCountJob = viewModelScope.launch {
            val result = repository.getBreedsImageById(count, breedsId)

            if (result is Either.Right) {
                _breedDetailsState.value = BreedDetailsUiState(
                    list = formDetailsStateList(result.right)
                )
            } else {
                _breedDetailsState.value = BreedDetailsUiState(
                    list = listOf(ErrorItem(NoInternetConnectionException()))
                )
            }
        }
    }

    private var searchJob: Job? = null
    fun search(breed: String) {
        searchText = breed
        searchJob?.cancel()
        Log.e("search", breed)
        searchJob = viewModelScope.launch {
            val resultList = breedsList?.filter {
                it.name.contains(breed.trim(), true)
            } ?: emptyList()

            if (resultList.isEmpty()) {
                _breedsListState.value = breedsListUiState.value!!.copy(
                    breedsList = listOf(HeaderItem(searchText), ErrorItem(SearchReturnedZeroItemsException()))
                )
                return@launch
            }
            _breedsListState.value = breedsListUiState.value!!.copy(
                breedsList = listOf(HeaderItem(searchText)) + resultList.map { BreedItem(Breed.from(it)) }
            )
        }

    }

    private var removeFromFavoritesJob: Job? = null
    fun removeFromFavorites(breedsId: String) {

    }

    private var addToFavoritesJob: Job? = null
    fun addToFavorites(descriptionItem: DescriptionItem) {

    }

    private fun formDetailsStateList(list: List<ImagesItem>): List<DelegateAdapterItem> {
        val breed = list.first().breeds.first()
        val characteristicsList = Characteristics.values().mapByBreed(breed)
        val descriptionItem = DescriptionItem(
            breedId = breed.id,
            breedName = breed.name,
            breedDescription = breed.description,
            breedTemperament = breed.temperament
        )
        val imagesItem = com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.models.ImagesItem(
            imagesUrlList = list.map { it.url }
        )
        val wikiItem = WikipediaItem(url = breed.wikipedia_url)

        return listOf(imagesItem, descriptionItem) + characteristicsList + listOf(wikiItem)
    }

    private fun Array<Characteristics>.mapByBreed(breed: com.example.android.catsapp.datalayer.catsbreeedsfeature.datamodels.getimages.Breed) =
        this.map {
            when(it) {
                Characteristics.AffectionLevel -> CharacteristicItem(it,breed.affection_level)
                Characteristics.Adaptability -> CharacteristicItem(it,breed.adaptability)
                Characteristics.ChildFriendly -> CharacteristicItem(it,breed.child_friendly)
                Characteristics.DogFriendly -> CharacteristicItem(it,breed.dog_friendly)
                Characteristics.EnergyLevel -> CharacteristicItem(it,breed.energy_level)
                Characteristics.Grooming -> CharacteristicItem(it,breed.grooming)
                Characteristics.HealthIssues -> CharacteristicItem(it,breed.health_issues)
                Characteristics.Intelligence -> CharacteristicItem(it,breed.intelligence)
                Characteristics.SheddingLevel -> CharacteristicItem(it,breed.shedding_level)
                Characteristics.SocialNeeds -> CharacteristicItem(it,breed.social_needs)
                Characteristics.StrangerFriendly -> CharacteristicItem(it,breed.stranger_friendly)
                Characteristics.Vocalisation -> CharacteristicItem(it,breed.vocalisation)
            }
        }

    override fun onCleared() {
        super.onCleared()
        fetchBreedsJob?.cancel()
        fetchBreedImagesByCountJob?.cancel()
        searchJob?.cancel()
    }
}