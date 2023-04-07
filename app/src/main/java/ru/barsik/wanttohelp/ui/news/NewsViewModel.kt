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
import ru.barsik.domain.model.Category
import ru.barsik.domain.model.Event
import ru.barsik.domain.usecase.GetAllEventsUseCase
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val getAllEventsUseCase: GetAllEventsUseCase,
    application: Application
) : AndroidViewModel(application) {

    private var filterCategories: ArrayList<Int>? = null
    private var allEvents: List<Event>? = null
    private val eventLiveData = MutableLiveData<List<Event>>()
    private val readEventIds: HashSet<Int> = HashSet()

    fun readEvent(eventId: Int) {
        readEventIds.add(eventId)
    }

    fun getReadIds() = readEventIds
    fun setFilterCategories(categoriesIdList: java.util.ArrayList<Int>) {
        filterCategories = categoriesIdList
        getAllEvents()
    }

    fun getEventListLD() = eventLiveData
    fun getAllEvents() {
        viewModelScope.launch {
            if (allEvents == null) {
                allEvents = getAllEventsUseCase.execute()
            }
            if (filterCategories == null) eventLiveData.postValue(allEvents!!)
            else {
                var eventRes = allEvents!!.filter { event ->
                    event.categories.intersect(filterCategories!!.toSet()).isNotEmpty()
                }
                eventLiveData.postValue(eventRes)
            }
        }
    }

}