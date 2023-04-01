package ru.barsik.data.datasource.local

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.barsik.data.datasource.CategoryDataSource
import ru.barsik.data.entity.CategoryEntity
import ru.barsik.domain.model.Category

/**
 *
 * @author Mitryashkin
 * @since 28.03.2023
 */
class CategoryLocalDataSource(private val ctx: Context) : CategoryDataSource {

    private val _fileName = "categories.json"
    override suspend fun getCategories(): List<CategoryEntity> {
        val jsonString = ctx.assets.open(_fileName)
            .bufferedReader().use { it.readText() }
        val gson = Gson()
        val catKeyType = object : TypeToken<List<CategoryEntity>>() {}.type
        return gson.fromJson(jsonString, catKeyType)
    }
}