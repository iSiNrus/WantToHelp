package ru.barsik.data.datasource.local

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.barsik.data.datasource.EventDataSource
import ru.barsik.domain.model.Event

class EventLocalDataSource(
    private val ctx: Context
    ) : EventDataSource{

    private val _filePath = "events.json"

    override suspend fun getEvents(): List<Event> {
        val jsonString = ctx.assets.open(_filePath)
            .bufferedReader().use { it.readText() }
        val gson = Gson()
        val eventKeyType = object : TypeToken<List<Event>>() {}.type
        return gson.fromJson(jsonString, eventKeyType) ?: ArrayList()
    }

}