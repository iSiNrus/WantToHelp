package ru.barsik.domain.usecase

import ru.barsik.domain.model.Event
import ru.barsik.domain.repository.EventRepository

class GetEventsByCategoryId(private val eventRepository: EventRepository) {

    suspend fun execute(categoryId: Int) : List<Event>{
        return eventRepository.getEventsByCategoryId(categoryId)
    }

}