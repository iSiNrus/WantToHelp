package ru.barsik.data.repository

import ru.barsik.data.datasource.local.EventLocalDataSource
import ru.barsik.data.datasource.remote.EventRemoteDataSource
import ru.barsik.domain.model.Event
import ru.barsik.domain.repository.EventRepository

class EventRepositoryImpl(
    private val localDS: EventLocalDataSource,
    private val remoteDS: EventRemoteDataSource
) : EventRepository {

    private var _eventList: List<Event>? = null

    override suspend fun getAllEvents(): List<Event> {
        if (_eventList == null)
            _eventList = localDS.getEvents()
        return _eventList!!
    }

    override suspend fun getEventById(id: Int): Event? {
        if (_eventList == null) getAllEvents()
        _eventList!!.forEach { if (it.id == id) return it }
        return null
    }

    override suspend fun getEventsByCategoryId(categoryId: Int): List<Event> {
        if (_eventList == null) getAllEvents()
        return _eventList!!.filter { event ->
            event.categories.contains(categoryId)
        }
    }

    override suspend fun getEventsByCategoriesIds(categoriesIds: List<Int>): List<Event> {
        if (_eventList == null) getAllEvents()

        return _eventList!!.filter { event ->
            event.categories.intersect(categoriesIds.toSet()).isNotEmpty()
        }
    }


}