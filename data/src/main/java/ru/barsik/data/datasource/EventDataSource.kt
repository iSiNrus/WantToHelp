package ru.barsik.data.datasource

import ru.barsik.data.entity.EventEntity

/**
 *
 * @author Mitryashkin
 * @since 28.03.2023
 */
interface EventDataSource {

    suspend fun getEvents(): List<EventEntity>

}