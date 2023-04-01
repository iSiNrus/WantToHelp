package ru.barsik.data.datasource.remote

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import ru.barsik.data.datasource.CategoryDataSource
import ru.barsik.data.entity.CategoryEntity
import ru.barsik.data.entity.EventEntity

/**
 *
 * @author Mitryashkin
 * @since 28.03.2023
 */
class CategoryRemoteDataSource : CategoryDataSource {
    override suspend fun getCategories(): List<CategoryEntity> {
        val ref = FirebaseDatabase.getInstance().reference.child("categories")
        val res = ref.get().await()
        val categories = ArrayList<CategoryEntity>()
        for (child in res.children)
            child.getValue(CategoryEntity::class.java)?.let { categories.add(it) }
        return categories
    }
}