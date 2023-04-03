package ru.barsik.wanttohelp.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.barsik.data.datasource.local.EventLocalDataSource
import ru.barsik.data.datasource.remote.EventRemoteDataSource
import ru.barsik.data.repository.EventRepositoryImpl
import ru.barsik.data.repository.ImageRepositoryImpl
import ru.barsik.domain.model.Event
import ru.barsik.domain.usecase.SearchEventByNKOUseCase
import ru.barsik.domain.usecase.SearchEventByTitleUseCase

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    var pagerAdapter: SearchPagerAdapter? = null

    private val searchEventQueryFlow = MutableStateFlow("").also { flow ->
        flow.debounce(500).onEach {
            eventSearchLiveData.postValue(searchEventUseCase.execute(it))
        }.launchIn(viewModelScope)
    }
    private val searchNkoQueryFlow = MutableStateFlow("").also { flow ->
        flow.debounce(500).onEach {
            nkoSearchEventsLiveData.postValue(searchNkoUseCase.execute(it))
        }.launchIn(viewModelScope)
    }

    private val nkoSearchEventsLiveData = MutableLiveData<List<Event>>()
    private val eventSearchLiveData = MutableLiveData<List<Event>>()

    private val searchNkoUseCase = SearchEventByNKOUseCase(
        EventRepositoryImpl(
            ImageRepositoryImpl(application),
            EventLocalDataSource(application),
            EventRemoteDataSource()
        )
    )
    private val searchEventUseCase = SearchEventByTitleUseCase(
        EventRepositoryImpl(
            ImageRepositoryImpl(application),
            EventLocalDataSource(application),
            EventRemoteDataSource()
        )
    )

    fun setEventQueryFlow(newQuery: String) {
        searchEventQueryFlow.value = newQuery
    }

    fun setNKOQueryFlow(newQuery: String) {
        searchNkoQueryFlow.value = newQuery
    }

    fun getNkoSearchLD() = nkoSearchEventsLiveData
    fun getEventSearchLD() = eventSearchLiveData
}