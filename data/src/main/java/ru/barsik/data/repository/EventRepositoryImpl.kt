package ru.barsik.data.repository

import android.util.Log
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import ru.barsik.data.datasource.local.EventLocalDataSource
import ru.barsik.data.datasource.remote.EventRemoteDataSource
import ru.barsik.data.mapper.toEvent
import ru.barsik.domain.model.Event
import ru.barsik.domain.repository.EventRepository
import ru.barsik.domain.repository.ImageRepository
import java.util.concurrent.TimeUnit

class EventRepositoryImpl(
    private val imageRepository: ImageRepository,
    private val localDS: EventLocalDataSource,
    private val remoteDS: EventRemoteDataSource
) : EventRepository {

    private var _eventList: List<Event>? = null

    override suspend fun getAllEvents(): List<Event> {
        if (_eventList == null) {
            try {
                withTimeout(5000) {
                    val eventEntityList = remoteDS.getEvents()
                    _eventList =
                        eventEntityList.map { x -> x.toEvent(imageRepository.getRemoteImage(x.title_img_path)) }
                }
            } catch (e : TimeoutCancellationException){
                Log.e("TIMEOUT", "getAllEvents", e)
                val eventEntityList = localDS.getEvents()
                _eventList =
                    eventEntityList.map { x -> x.toEvent(imageRepository.getLocalImage(x.title_img_path)) }
            }
        }
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

    override suspend fun searchEventByNKO(query: String): List<Event> {
        if(query.isEmpty()) return emptyList()
        if (_eventList == null) getAllEvents()
        return _eventList!!.filter { x-> x.organization.contains(query, true) }
    }

    override suspend fun searchEventByTitle(query: String): List<Event> {
        if(query.isEmpty()) return emptyList()
        if (_eventList == null) getAllEvents()
        return _eventList!!.filter { x-> x.title.contains(query, true) }
    }


}