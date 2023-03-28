package ru.barsik.data.repository

import ru.barsik.domain.model.Category
import ru.barsik.domain.repository.CategoryRepository

/**
 *
 * @author Mitryashkin
 * @since 28.03.2023
 */
class CategoryRepositoryImpl : CategoryRepository {
    override suspend fun getAllCategories(): List<Category> {
        TODO("Not yet implemented")
    }
}