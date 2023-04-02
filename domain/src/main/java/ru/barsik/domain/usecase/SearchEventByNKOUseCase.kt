package ru.barsik.domain.usecase

import ru.barsik.domain.model.Event
import ru.barsik.domain.repository.EventRepository

class SearchEventByNKOUseCase(
    private val eventRepository: EventRepository
) {
    suspend fun execute(query: String) : List<Event>{
        return eventRepository.searchEventByNKO(query)
    }
}