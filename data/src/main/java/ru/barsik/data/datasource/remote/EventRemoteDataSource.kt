package ru.barsik.data.datasource.remote

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import ru.barsik.data.datasource.EventDataSource
import ru.barsik.data.entity.EventEntity

class EventRemoteDataSource : EventDataSource{
    override suspend fun getEvents(): List<EventEntity> {
        val ref = FirebaseDatabase.getInstance().reference.child("events")
        val res = ref.get().await()
        val events = ArrayList<EventEntity>()
        for (child in res.children)
            child.getValue(EventEntity::class.java)?.let { events.add(it) }
        return events
    }
}