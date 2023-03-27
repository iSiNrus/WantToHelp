package ru.barsik.data.repository

import ru.barsik.data.datasource.EventLocalDataSource
import ru.barsik.data.datasource.EventRemoteDataSource
import ru.barsik.domain.model.Event
import ru.barsik.domain.repository.EventRepository

class EventRepositoryImpl(
    private val localDS: EventLocalDataSource,
    private val remoteDS: EventRemoteDataSource
) : EventRepository {

    override suspend fun getAllEvents(): List<Event> {
        TODO("Not yet implemented")
    }

    override suspend fun getEventById(id: Int): Event {
        TODO("Not yet implemented")
    }

    override suspend fun getEventsByCategoryId(categoryId: Int): List<Event> {
        TODO("Not yet implemented")
    }


}