package ru.barsik.data.datasource

import ru.barsik.domain.model.Event

/**
 *
 * @author Mitryashkin
 * @since 28.03.2023
 */
interface EventDataSource {

    suspend fun getEvents(): List<Event>

}