package ru.barsik.domain.usecase

import ru.barsik.domain.model.Event
import ru.barsik.domain.repository.EventRepository

class GetEventByIdUseCase(private val eventRepository: EventRepository) {

    suspend fun execute(id: Int): Event {
        return eventRepository.getEventById(id)
    }

}