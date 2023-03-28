package ru.barsik.wanttohelp.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.barsik.domain.usecase.GetAllEventsUseCase

class NewsViewModel(private val getAllEventsUseCase: GetAllEventsUseCase) : ViewModel() {

    //LiveData events

    fun getAllEvents(){
        viewModelScope.launch {
            getAllEventsUseCase.execute()
        }
    }

}