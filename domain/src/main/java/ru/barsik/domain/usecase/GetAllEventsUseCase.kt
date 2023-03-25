package ru.barsik.domain.usecase

import ru.barsik.domain.model.Event
import ru.barsik.domain.repository.EventRepository

class GetAllEventsUseCase(private val eventRepository: EventRepository) {

    suspend fun execute() : List<Event>{
        return eventRepository.getAllEvents()
    }

}