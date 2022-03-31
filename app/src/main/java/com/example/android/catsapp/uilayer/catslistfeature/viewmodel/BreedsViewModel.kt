package com.example.android.catsapp.uilayer.catslistfeature.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.catsapp.datalayer.catsbreeedsfeature.CatsRepository
import com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource.datamodels.FullBreedInfo
import com.example.android.catsapp.datalayer.catsbreeedsfeature.remotedatasource.datamodels.getbreeds.BreedsItem
import com.example.android.catsapp.domainlayer.Either
import com.example.android.catsapp.domainlayer.NoInternetConnectionException
import com.example.android.catsapp.domainlayer.SearchReturnedZeroItemsException
import com.example.android.catsapp.uilayer.catslistfeature.datamodels.Breed
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.models.*
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.models.BreedItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.models.ErrorItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.models.HeaderItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedslist.recycler.models.LoadingItem
import com.example.android.catsapp.uilayer.catslistfeature.uistate.BreedDetailsUiState
import com.example.android.catsapp.uilayer.catslistfeature.uistate.BreedsListUiState
import com.example.android.catsapp.uilayer.catslistfeature.uistate.FavoriteListUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.IllegalStateException

class BreedsViewModel(
    private val repository: CatsRepository
) : ViewModel() {
    private val _breedsListState =
        MutableLiveData(BreedsListUiState(breedsList = listOf(LoadingItem())))
    val breedsListUiState: LiveData<BreedsListUiState>
        get() = _breedsListState

    private val _breedDetailsState =
        MutableLiveData(BreedDetailsUiState(list = listOf(LoadingItem())))
    val breedDetailsState: LiveData<BreedDetailsUiState>
        get() = _breedDetailsState

    private val _favoriteListState =
        MutableLiveData(FavoriteListUiState(listFavorites = emptyList()))
    val favoritesListState: LiveData<FavoriteListUiState>
        get() = _favoriteListState

    private var searchText: String = ""
    private var breedsList: List<BreedsItem>? = null
    private var breedInfo: FullBreedInfo? = null

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
                    breedsList = listOf(HeaderItem(searchText)) + result.right.map {
                        BreedItem(
                            Breed.from(
                                it
                            )
                        )
                    }
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
                breedInfo = result.right
                _breedDetailsState.value = BreedDetailsUiState(
                    list = formDetailsStateList(breedInfo!!)
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
                    breedsList = listOf(
                        HeaderItem(searchText),
                        ErrorItem(SearchReturnedZeroItemsException())
                    )
                )
                return@launch
            }
            _breedsListState.value = breedsListUiState.value!!.copy(
                breedsList = listOf(HeaderItem(searchText)) + resultList.map {
                    BreedItem(
                        Breed.from(
                            it
                        )
                    )
                }
            )
        }

    }

    private var removeFromFavoritesJob: Job? = null
    fun removeFromFavorites(breedId: String) {
        removeFromFavoritesJob?.cancel()

        removeFromFavoritesJob = viewModelScope.launch {
            repository.deleteFromFavorites(breedId)

            _favoriteListState.value = FavoriteListUiState(
                listFavorites = favoritesListState.value!!.listFavorites.filter { it.id != breedId }
            )

            breedInfo?.let {
                if (it.id == breedId) {
                    breedDetailsState.value?.list?.getOrNull(1)?.let {
                        if ((it as DescriptionItem).breedId == breedId) {
                            _breedDetailsState.value = BreedDetailsUiState(
                                (breedDetailsState.value!!.list.toMutableList()).apply {
                                    set(1, it.copy(isFavorite = false))
                                }
                            )
                        }
                    }
                    breedInfo = it.copy(isSelected = false)
                }
            }
        }
    }

    private var addToFavoritesJob: Job? = null
    fun addToFavorites(breedId: String) {
        addToFavoritesJob?.cancel()

        if (breedInfo?.id != breedId) throw IllegalStateException()

        addToFavoritesJob = viewModelScope.launch {
            repository.addToFavorites(breedInfo!!)

            _breedDetailsState.value = BreedDetailsUiState(
                (breedDetailsState.value!!.list.toMutableList()).apply {
                    set(
                        1,
                        (breedDetailsState.value!!.list[1] as DescriptionItem).copy(isFavorite = true)
                    )
                }
            )
            breedInfo = breedInfo!!.copy(isSelected = true)
        }
    }

    private var fetchFavoritesJob: Job? = null
    fun fetchFavorites() {
        fetchFavoritesJob?.cancel()

        fetchFavoritesJob = viewModelScope.launch {
            val favoritesList = repository.getAllFavorites()
            _favoriteListState.value = FavoriteListUiState(favoritesList)
        }
    }

    private fun formDetailsStateList(breed: FullBreedInfo): List<DelegateAdapterItem> {
        val characteristicsList = Characteristics.values().mapByBreed(breed)
        val descriptionItem = DescriptionItem(
            breedId = breed.id,
            breedName = breed.name,
            breedDescription = breed.description,
            breedTemperament = breed.temperament,
            isFavorite = breed.isSelected
        )
        val imagesItem = ImagesItem(imagesUrlList = breed.imagesUrl)
        val wikiItem = WikipediaItem(url = breed.wikiUrl)

        return listOf(imagesItem, descriptionItem) + characteristicsList + listOf(wikiItem)
    }

    private fun Array<Characteristics>.mapByBreed(breed: FullBreedInfo) =
        this.map {
            when (it) {
                Characteristics.AffectionLevel -> CharacteristicItem(it, breed.affectionLevel)
                Characteristics.Adaptability -> CharacteristicItem(it, breed.adaptability)
                Characteristics.ChildFriendly -> CharacteristicItem(it, breed.childFriendly)
                Characteristics.DogFriendly -> CharacteristicItem(it, breed.dogFriendly)
                Characteristics.EnergyLevel -> CharacteristicItem(it, breed.energyLevel)
                Characteristics.Grooming -> CharacteristicItem(it, breed.grooming)
                Characteristics.HealthIssues -> CharacteristicItem(it, breed.healthIssues)
                Characteristics.Intelligence -> CharacteristicItem(it, breed.intelligence)
                Characteristics.SheddingLevel -> CharacteristicItem(it, breed.sheddingLevel)
                Characteristics.SocialNeeds -> CharacteristicItem(it, breed.socialNeeds)
                Characteristics.StrangerFriendly -> CharacteristicItem(it, breed.strangerFriendly)
                Characteristics.Vocalisation -> CharacteristicItem(it, breed.vocalisation)
            }
        }

    override fun onCleared() {
        super.onCleared()
        fetchBreedsJob?.cancel()
        fetchBreedImagesByCountJob?.cancel()
        searchJob?.cancel()
    }
}