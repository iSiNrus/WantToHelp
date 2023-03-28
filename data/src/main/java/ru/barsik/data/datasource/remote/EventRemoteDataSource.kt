package ru.barsik.data.datasource.remote

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import ru.barsik.data.datasource.EventDataSource
import ru.barsik.domain.model.Event

class EventRemoteDataSource : EventDataSource{
    override suspend fun getEvents(): List<Event> {
        val ref = FirebaseDatabase.getInstance().reference.child("events")
        val res = ref.get().await()
        val events = ArrayList<Event>()
        for (child in res.children)
            child.getValue(Event::class.java)?.let { events.add(it) }
        return events
    }
}