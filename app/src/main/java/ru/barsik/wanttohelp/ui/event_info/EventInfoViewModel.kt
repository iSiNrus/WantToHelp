package ru.barsik.wanttohelp.ui.event_info

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.barsik.domain.model.Event
import ru.barsik.domain.usecase.GetEventByIdUseCase
import javax.inject.Inject

class EventInfoViewModel @Inject constructor(
    private val getEventByIdUseCase: GetEventByIdUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val eventLiveData = MutableLiveData<Event>()

    fun getEventLD() = eventLiveData

    fun getEvent(eventId: Int) {
        viewModelScope.launch {
            eventLiveData.postValue(getEventByIdUseCase.execute(eventId))
        }
    }

}