package test.test.testLastFM.ui.main

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import test.test.testLastFM.api.LastFmApi
import test.test.testLastFM.api.response.TopArtistsResponse
import test.test.testLastFM.data.DataRepository
import test.test.testLastFM.data.DatabaseDao
import test.test.testLastFM.data.entities.ArtistEntity
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mRepository: DataRepository,
) : ViewModel() {
    private val artistsFlow = mRepository.getArtists()
    val artists: LiveData<List<ArtistEntity>>
        get() = artistsFlow.asLiveData()

    private val refreshingState = MutableLiveData(false)
    val refreshing: LiveData<Boolean>
        get() = refreshingState

    fun fetchData() {
        viewModelScope.launch {
            mRepository.fetchData()
           refreshingState.value = false
        }
    }
}