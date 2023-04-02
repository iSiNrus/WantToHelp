package ru.barsik.domain.repository

import ru.barsik.domain.model.Event

interface EventRepository {

    suspend fun getAllEvents() : List<Event>

    suspend fun getEventById(id : Int) : Event?

    suspend fun getEventsByCategoryId(categoryId: Int) : List<Event>

    suspend fun getEventsByCategoriesIds(categoriesIds: List<Int>): List<Event>
    suspend fun searchEventByNKO(query: String): List<Event>
    suspend fun searchEventByTitle(query: String): List<Event>

}