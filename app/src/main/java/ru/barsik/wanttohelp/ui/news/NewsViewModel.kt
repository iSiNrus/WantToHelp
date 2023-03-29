package ru.barsik.wanttohelp.ui.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.barsik.data.datasource.local.EventLocalDataSource
import ru.barsik.data.datasource.remote.EventRemoteDataSource
import ru.barsik.data.repository.EventRepositoryImpl
import ru.barsik.data.repository.ImageRepositoryImpl
import ru.barsik.domain.model.Event
import ru.barsik.domain.usecase.GetAllEventsUseCase

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val getAllEventsUseCase =
        GetAllEventsUseCase(
            EventRepositoryImpl(
                ImageRepositoryImpl(application),
                EventLocalDataSource(application),
                EventRemoteDataSource()
            )
        )

    private val eventLiveData = MutableLiveData<List<Event>>()

    fun getEventListLD() = eventLiveData
    fun getAllEvents() {
        viewModelScope.launch {
            val res = getAllEventsUseCase.execute()
            if (res.isNotEmpty()) {
                eventLiveData.postValue(res)
            }
        }
    }

}