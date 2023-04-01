package ru.barsik.wanttohelp.ui.event_info

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
import ru.barsik.domain.usecase.GetEventByIdUseCase

class EventInfoViewModel(application: Application) : AndroidViewModel(application) {

    private val eventLiveData = MutableLiveData<Event>()

    private val getEventByIdUseCase = GetEventByIdUseCase(
        EventRepositoryImpl(
            ImageRepositoryImpl(application),
            EventLocalDataSource(application),
            EventRemoteDataSource()
        )
    )

    fun getEventLD() = eventLiveData

    fun getEvent(eventId: Int) {
        viewModelScope.launch {
            eventLiveData.postValue(getEventByIdUseCase.execute(eventId))
        }
    }

}